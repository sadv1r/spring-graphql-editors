package ru.sadv1r.spring.graphql.editor.playground.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@AutoConfiguration
@EnableConfigurationProperties(PlaygroundProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnProperty(value = "spring.graphql.playground.enabled", havingValue = "true", matchIfMissing = true)
public class PlaygroundWebFluxMvcAutoConfiguration {

    @Bean
    @Order(-1)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    public RouterFunction<ServerResponse> reactivePlaygroundRouterFunction(PlaygroundProperties properties,
                                                                           @Value("${spring.graphql.path:/graphql}") String serverPath) {
        RouterFunctions.Builder builder = RouterFunctions.route();
        if (properties.isEnabled()) {
            final HandlerFunction<ServerResponse> handler = e -> (Mono<ServerResponse>) (Mono<?>) RenderingResponse.create("playground")
                    .modelAttribute("cdnHost", properties.getCdn().getHost())
                    .modelAttribute("serverPath", serverPath)
                    .modelAttribute("settings", properties.getSettings())
                    .modelAttribute("headers", properties.getHeaders())
                    .modelAttribute("tabs", properties.getTabs())
                    .build();
            builder = builder.GET(properties.getPath(), handler);
        }
        return builder.build();
    }

}
