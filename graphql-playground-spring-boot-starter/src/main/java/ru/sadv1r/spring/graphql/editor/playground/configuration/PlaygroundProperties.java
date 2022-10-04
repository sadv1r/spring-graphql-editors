package ru.sadv1r.spring.graphql.editor.playground.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Configuration properties for the Playground GraphQL editor.
 *
 * @see <a href="https://github.com/graphql/graphql-playground#properties">GraphQL Playground Properties</a>
 */
@ConfigurationProperties(prefix = "spring.graphql.playground")
public class PlaygroundProperties {

    private boolean enabled = true;

    private String path = "/playground";

    private Settings settings = new Settings();

    private Map<String, String> headers = Collections.emptyMap();

    private List<Tab> tabs = Collections.emptyList();

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

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }

    public Cdn getCdn() {
        return cdn;
    }

    public void setCdn(Cdn cdn) {
        this.cdn = cdn;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Settings {

        @JsonUnwrapped(prefix = "editor.")
        private Editor editor;

        @JsonUnwrapped(prefix = "general.")
        private General general;

        @JsonUnwrapped(prefix = "prettier.")
        private Prettier prettier;

        @JsonUnwrapped(prefix = "request.")
        private Request request;

        @JsonUnwrapped(prefix = "schema.")
        private Schema schema;

        @JsonUnwrapped(prefix = "tracing.")
        private Tracing tracing;

        public Editor getEditor() {
            return editor;
        }

        public void setEditor(Editor editor) {
            this.editor = editor;
        }

        public General getGeneral() {
            return general;
        }

        public void setGeneral(General general) {
            this.general = general;
        }

        public Prettier getPrettier() {
            return prettier;
        }

        public void setPrettier(Prettier prettier) {
            this.prettier = prettier;
        }

        public Request getRequest() {
            return request;
        }

        public void setRequest(Request request) {
            this.request = request;
        }

        public Schema getSchema() {
            return schema;
        }

        public void setSchema(Schema schema) {
            this.schema = schema;
        }

        public Tracing getTracing() {
            return tracing;
        }

        public void setTracing(Tracing tracing) {
            this.tracing = tracing;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private static class Editor {

            private CursorShape cursorShape;

            private String fontFamily;

            private Integer fontSize;

            private Boolean reuseHeaders;

            private Theme theme;

            public CursorShape getCursorShape() {
                return cursorShape;
            }

            public void setCursorShape(CursorShape cursorShape) {
                this.cursorShape = cursorShape;
            }

            public String getFontFamily() {
                return fontFamily;
            }

            public void setFontFamily(String fontFamily) {
                this.fontFamily = fontFamily;
            }

            public Integer getFontSize() {
                return fontSize;
            }

            public void setFontSize(Integer fontSize) {
                this.fontSize = fontSize;
            }

            public Boolean getReuseHeaders() {
                return reuseHeaders;
            }

            public void setReuseHeaders(Boolean reuseHeaders) {
                this.reuseHeaders = reuseHeaders;
            }

            public Theme getTheme() {
                return theme;
            }

            public void setTheme(Theme theme) {
                this.theme = theme;
            }

            private enum CursorShape {
                @JsonProperty("line")
                LINE,
                @JsonProperty("block")
                BLOCK,
                @JsonProperty("underline")
                UNDERLINE
            }

            private enum Theme {
                @JsonProperty("light")
                LIGHT,
                @JsonProperty("dark")
                DARK
            }

        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private static class General {

            private Boolean betaUpdates;

            public Boolean getBetaUpdates() {
                return betaUpdates;
            }

            public void setBetaUpdates(Boolean betaUpdates) {
                this.betaUpdates = betaUpdates;
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private static class Prettier {

            private Integer printWidth;

            private Integer tabWidth;

            private Boolean useTabs;

            public Integer getPrintWidth() {
                return printWidth;
            }

            public void setPrintWidth(Integer printWidth) {
                this.printWidth = printWidth;
            }

            public Integer getTabWidth() {
                return tabWidth;
            }

            public void setTabWidth(Integer tabWidth) {
                this.tabWidth = tabWidth;
            }

            public Boolean getUseTabs() {
                return useTabs;
            }

            public void setUseTabs(Boolean useTabs) {
                this.useTabs = useTabs;
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private static class Request {

            private Credentials credentials;

            public Credentials getCredentials() {
                return credentials;
            }

            public void setCredentials(Credentials credentials) {
                this.credentials = credentials;
            }

            public enum Credentials {
                @JsonProperty("omit")
                OMIT,
                @JsonProperty("include")
                INCLUDE,
                @JsonProperty("same-origin")
                SAME_ORIGIN
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private static class Schema {

            @JsonUnwrapped(prefix = "polling.")
            private Polling polling;

            private Boolean disableComments;

            public Polling getPolling() {
                return polling;
            }

            public void setPolling(Polling polling) {
                this.polling = polling;
            }

            public Boolean getDisableComments() {
                return disableComments;
            }

            public void setDisableComments(Boolean disableComments) {
                this.disableComments = disableComments;
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            private class Polling {

                private Boolean enable;

                private String endpointFilter;

                private Integer interval;

                public Boolean getEnable() {
                    return enable;
                }

                public void setEnable(Boolean enable) {
                    this.enable = enable;
                }

                public String getEndpointFilter() {
                    return endpointFilter;
                }

                public void setEndpointFilter(String endpointFilter) {
                    this.endpointFilter = endpointFilter;
                }

                public Integer getInterval() {
                    return interval;
                }

                public void setInterval(Integer interval) {
                    this.interval = interval;
                }
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private static class Tracing {

            private Boolean hideTracingResponse;

            private Boolean tracingSupported;

            public Boolean getHideTracingResponse() {
                return hideTracingResponse;
            }

            public void setHideTracingResponse(Boolean hideTracingResponse) {
                this.hideTracingResponse = hideTracingResponse;
            }

            public Boolean getTracingSupported() {
                return tracingSupported;
            }

            public void setTracingSupported(Boolean tracingSupported) {
                this.tracingSupported = tracingSupported;
            }
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class Tab {

        private String endpoint;

        private String query;

        private String name;

//        private Object variables; TODO

//        private Object responses; TODO

        private Map<String, String> headers;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = readIfPath(query);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }
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
