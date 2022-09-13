package ru.sadv1r.spring.graphql.editor.voyager.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.sadv1r.spring.graphql.editor.voyager.ReactiveVoyagerController;
import ru.sadv1r.spring.graphql.editor.voyager.ServletVoyagerController;

@AutoConfiguration
@ConditionalOnProperty(value = "spring.graphql.voyager.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(VoyagerProperties.class)
public class VoyagerAutoConfiguration {

    @Bean(name = "voyagerController")
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public ServletVoyagerController servletVoyagerController(VoyagerProperties properties) {
        return new ServletVoyagerController(properties);
    }

    @Bean(name = "voyagerController")
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    public ReactiveVoyagerController reactiveVoyagerController(VoyagerProperties properties) {
        return new ReactiveVoyagerController(properties);
    }

}
