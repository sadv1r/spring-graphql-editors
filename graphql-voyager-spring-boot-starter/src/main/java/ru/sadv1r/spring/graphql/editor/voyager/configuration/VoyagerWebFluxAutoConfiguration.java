package ru.sadv1r.spring.graphql.editor.voyager.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RenderingResponse;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@AutoConfiguration
@EnableConfigurationProperties(VoyagerProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnProperty(value = "spring.graphql.voyager.enabled", havingValue = "true", matchIfMissing = true)
public class VoyagerWebFluxAutoConfiguration {

    @Bean
    @Order(-1)
    public RouterFunction<RenderingResponse> reactiveVoyagerRouterFunction(VoyagerProperties properties,
                                                                           @Value("${spring.graphql.path:/graphql}") String serverPath) {
        String pathWithContext = serverPath.startsWith("/") ? serverPath : "/" + serverPath;

        final HandlerFunction<RenderingResponse> handler = e -> RenderingResponse.create("voyager")
                .modelAttribute("cdnHost", properties.getCdn().getHost())
                .modelAttribute("serverPath", e.requestPath().contextPath().value() + pathWithContext)
                .modelAttribute("displayOptions", properties.getDisplayOptions())
                .modelAttribute("allowToChangeSchema", properties.isAllowToChangeSchema())
                .modelAttribute("hideDocs", properties.isHideDocs())
                .modelAttribute("hideSettings", properties.isHideSettings())
                .modelAttribute("hideVoyagerLogo", properties.isHideVoyagerLogo())
                .modelAttribute("headers", properties.getHeaders())
                .modelAttribute("stylePath", properties.getStylePath())
                .build();

        return RouterFunctions.route(GET(properties.getPath()), handler);
    }

}
