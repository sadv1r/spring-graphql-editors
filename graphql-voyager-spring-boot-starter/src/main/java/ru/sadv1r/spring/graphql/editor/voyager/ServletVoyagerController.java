package ru.sadv1r.spring.graphql.editor.voyager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sadv1r.spring.graphql.editor.voyager.configuration.VoyagerProperties;

@Controller
public class ServletVoyagerController {

    private final VoyagerProperties properties;

    @Value("${spring.graphql.path:/graphql}")
    private String serverPath;

    public ServletVoyagerController(VoyagerProperties properties) {
        this.properties = properties;
    }

    @GetMapping(value = "${spring.graphql.voyager.path:/voyager}")
    public String voyager(Model model) {
        model
                .addAttribute("cdnHost", properties.getCdn().getHost())
                .addAttribute("serverPath", serverPath)
                .addAttribute("displayOptions", properties.getDisplayOptions())
                .addAttribute("hideDocs", properties.isHideDocs())
                .addAttribute("hideSettings", properties.isHideSettings())
                .addAttribute("stylePath", properties.getStylePath());
        return "voyager";
    }

}
