package ru.sadv1r.spring.graphql.editor.graphiql.configuration;

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
@EnableConfigurationProperties(GraphiqlProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(value = "spring.graphql.graphiql.enabled", havingValue = "true", matchIfMissing = true)
public class GraphiQlWebMvcAutoConfiguration {

    @Bean
    @Order(-1)
    public RouterFunction<ServerResponse> graphiQlRouterFunction(GraphiqlProperties properties,
                                                                 @Value("${spring.graphql.path:/graphql}") String serverPath) {
        String pathWithContext = serverPath.startsWith("/") ? serverPath : "/" + serverPath;

        final HandlerFunction<ServerResponse> handler = e -> RenderingResponse.create("graphiql")
                .modelAttribute("cdnHost", properties.getCdn().getHost())
                .modelAttribute("serverPath", e.requestPath().contextPath().value() + pathWithContext)
                .modelAttribute("defaultQuery", properties.getDefaultQuery())
                .modelAttribute("query", properties.getQuery())
                .modelAttribute("variables", properties.getVariables())
                .modelAttribute("headers", properties.getHeaders())
                .modelAttribute("defaultEditorToolsVisibility", properties.getDefaultEditorToolsVisibility())
                .modelAttribute("plugins", properties.getPlugins())
                .modelAttribute("stylePath", properties.getStylePath())
                .build();

        return RouterFunctions.route(GET(properties.getPath()), handler);
    }

}
