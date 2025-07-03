package ru.sadv1r.spring.graphql.editor.graphiql.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 * Configuration properties for the GraphiQL GraphQL editor.
 *
 * @see <a href="https://github.com/graphql/graphiql/blob/main/packages/graphiql/README.md#props">GraphQL GraphiQL Properties</a>
 */
@ConfigurationProperties(prefix = "spring.graphql.graphiql")
public class GraphiqlProperties {

    private boolean enabled = true;

    private String path = "/graphiql";

    private String defaultQuery;

    private String query;

    private Map<String, String> variables = Map.of();

    private Map<String, String> headers = Map.of();

    private DefaultEditorToolsVisibility defaultEditorToolsVisibility = DefaultEditorToolsVisibility.SHOWN;

    private Set<Plugin> plugins = Set.of();

    private Cdn cdn = Cdn.UNPKG;

    private String stylePath;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDefaultQuery() {
        return defaultQuery;
    }

    public void setDefaultQuery(String defaultQuery) {
        this.defaultQuery = readIfPath(defaultQuery);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = readIfPath(query);
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public DefaultEditorToolsVisibility getDefaultEditorToolsVisibility() {
        return defaultEditorToolsVisibility;
    }

    public void setDefaultEditorToolsVisibility(DefaultEditorToolsVisibility defaultEditorToolsVisibility) {
        this.defaultEditorToolsVisibility = defaultEditorToolsVisibility;
    }

    public Set<Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(Set<Plugin> plugins) {
        this.plugins = plugins;
    }

    public Cdn getCdn() {
        return cdn;
    }

    public void setCdn(Cdn cdn) {
        this.cdn = cdn;
    }

    public String getStylePath() {
        return stylePath;
    }

    public void setStylePath(String stylePath) {
        this.stylePath = stylePath;
    }

    private static String readIfPath(String query) {
        if (query == null) {
            return null;
        }

        if (query.matches("^.*\\.graphql$")) {
            try (Reader reader = new InputStreamReader(new ClassPathResource(query).getInputStream(), StandardCharsets.UTF_8)) {
                return FileCopyUtils.copyToString(reader);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        return query;
    }

    public enum DefaultEditorToolsVisibility {
        SHOWN(true),
        HIDDEN(false),
        VARIABLES("'variables'"),
        HEADERS("'headers'");

        private final Object value;

        DefaultEditorToolsVisibility(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }
    }

    public enum Plugin {
        /**
         * @see <a href="https://github.com/graphql/graphiql/tree/main/packages/graphiql-plugin-explorer">GraphiQL Explorer Plugin</a>
         */
        EXPLORER
    }

    public enum Cdn {
        JSDELIVR,
        UNPKG;

        public String getHost() {
            switch (this) {
                case JSDELIVR:
                    return "https://cdn.jsdelivr.net/npm";
                case UNPKG:
                    return "https://unpkg.com";
                default:
                    throw new IllegalArgumentException();
            }
        }

    }

}
