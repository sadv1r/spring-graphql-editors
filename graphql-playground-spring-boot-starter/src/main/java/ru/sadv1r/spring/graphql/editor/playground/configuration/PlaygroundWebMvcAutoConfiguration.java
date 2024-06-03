package ru.sadv1r.spring.graphql.editor.playground.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.function.*;

import static org.springframework.web.servlet.function.RequestPredicates.GET;

@AutoConfiguration
@EnableConfigurationProperties(PlaygroundProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(value = "spring.graphql.playground.enabled", havingValue = "true", matchIfMissing = true)
public class PlaygroundWebMvcAutoConfiguration {

    @Bean
    @Order(-1)
    public RouterFunction<ServerResponse> playgroundRouterFunction(PlaygroundProperties properties,
                                                                   @Value("${spring.graphql.path:/graphql}") String serverPath) {
        String pathWithContext = serverPath.startsWith("/") ? serverPath : "/" + serverPath;

        final HandlerFunction<ServerResponse> handler = e -> RenderingResponse.create("playground")
                .modelAttribute("cdnHost", properties.getCdn().getHost())
                .modelAttribute("serverPath", e.requestPath().contextPath().value() + pathWithContext)
                .modelAttribute("settings", properties.getSettings())
                .modelAttribute("headers", properties.getHeaders())
                .modelAttribute("tabs", properties.getTabs())
                .build();

        return RouterFunctions.route(GET(properties.getPath()), handler);
    }

}
