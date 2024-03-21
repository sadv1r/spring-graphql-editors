package ru.sadv1r.spring.graphql.editor.voyager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;
import ru.sadv1r.spring.graphql.editor.voyager.configuration.VoyagerProperties;

@Controller
public class ReactiveVoyagerController {

    private final VoyagerProperties properties;

    @Value("${spring.graphql.path:/graphql}")
    private String serverPath;

    public ReactiveVoyagerController(VoyagerProperties properties) {
        this.properties = properties;
    }

    @GetMapping(value = "${spring.graphql.voyager.path:/voyager}")
    public Mono<Rendering> voyager() {
        return Mono.just(
                Rendering.view("voyager")
                        .modelAttribute("cdnHost", properties.getCdn().getHost())
                        .modelAttribute("serverPath", serverPath)
                        .modelAttribute("displayOptions", properties.getDisplayOptions())
                        .modelAttribute("hideDocs", properties.isHideDocs())
                        .modelAttribute("hideSettings", properties.isHideSettings())
                        .modelAttribute("stylePath", properties.getStylePath())
                        .build()
        );
    }

}
