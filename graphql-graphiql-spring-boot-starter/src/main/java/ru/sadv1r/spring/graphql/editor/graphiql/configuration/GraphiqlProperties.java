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

/**
 * Configuration properties for the GraphiQL GraphQL editor.
 *
 * @see <a href="https://github.com/graphql/graphiql/blob/main/packages/graphiql/README.md#props">GraphQL GraphiQL Properties</a>
 */
@ConfigurationProperties(prefix = "spring.graphql.graphiql")
public class GraphiqlProperties {

    private boolean enabled = true;

    private String path = "/graphiql";

    private String query;

    private DefaultEditorToolsVisibility defaultEditorToolsVisibility = DefaultEditorToolsVisibility.SHOWN;

    private Map<String, String> variables;

    private Map<String, String> headers;

    private Cdn cdn = Cdn.UNPKG;

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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = readIfPath(query);
    }

    public DefaultEditorToolsVisibility getDefaultEditorToolsVisibility() {
        return defaultEditorToolsVisibility;
    }

    public void setDefaultEditorToolsVisibility(DefaultEditorToolsVisibility defaultEditorToolsVisibility) {
        this.defaultEditorToolsVisibility = defaultEditorToolsVisibility;
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

    public Cdn getCdn() {
        return cdn;
    }

    public void setCdn(Cdn cdn) {
        this.cdn = cdn;
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
