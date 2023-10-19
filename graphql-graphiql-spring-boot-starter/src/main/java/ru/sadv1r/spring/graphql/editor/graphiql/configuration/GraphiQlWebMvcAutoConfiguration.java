package ru.sadv1r.spring.graphql.editor.graphiql.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.function.*;

@AutoConfiguration
@EnableConfigurationProperties(GraphiqlProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(value = "spring.graphql.graphiql.enabled", havingValue = "true", matchIfMissing = true)
public class GraphiQlWebMvcAutoConfiguration {

    @Bean
    @Order(-1)
    public RouterFunction<ServerResponse> graphiQlRouterFunction(GraphiqlProperties properties,
                                                                 @Value("${spring.graphql.path:/graphql}") String serverPath) {
        RouterFunctions.Builder builder = RouterFunctions.route();
        if (properties.isEnabled()) {
            final HandlerFunction<ServerResponse> graphiql = e -> RenderingResponse.create("graphiql")
                    .modelAttribute("cdnHost", properties.getCdn().getHost())
                    .modelAttribute("serverPath", serverPath)
                    .modelAttribute("query", properties.getQuery())
                    .modelAttribute("defaultEditorToolsVisibility", properties.getDefaultEditorToolsVisibility())
                    .modelAttribute("variables", properties.getVariables())
                    .modelAttribute("headers", properties.getHeaders())
                    .build();
            builder = builder.GET(properties.getPath(), graphiql);
        }
        return builder.build();
    }

}
