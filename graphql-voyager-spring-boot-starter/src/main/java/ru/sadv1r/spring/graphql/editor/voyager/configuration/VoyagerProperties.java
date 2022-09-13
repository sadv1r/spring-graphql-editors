package ru.sadv1r.spring.graphql.editor.voyager.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Configuration properties for the Voyager GraphQL editor.
 *
 * @see <a href="https://github.com/IvanGoncharov/graphql-voyager#properties">GraphQL Voyager Properties</a>
 */
@ConfigurationProperties(prefix = "spring.graphql.voyager")
public class VoyagerProperties {

    private boolean enabled = true;

    private String path = "/voyager";

    @NestedConfigurationProperty
    private DisplayOptions displayOptions = new DisplayOptions();

    /**
     * Hide the docs sidebar
     */
    private boolean hideDocs = false;

    /**
     * Hide settings panel
     */
    private boolean hideSettings = false;

    private Cdn cdn = Cdn.JSDELIVR;

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

    public DisplayOptions getDisplayOptions() {
        return displayOptions;
    }

    public void setDisplayOptions(DisplayOptions displayOptions) {
        this.displayOptions = displayOptions;
    }

    public boolean isHideDocs() {
        return hideDocs;
    }

    public void setHideDocs(boolean hideDocs) {
        this.hideDocs = hideDocs;
    }

    public boolean isHideSettings() {
        return hideSettings;
    }

    public void setHideSettings(boolean hideSettings) {
        this.hideSettings = hideSettings;
    }

    public Cdn getCdn() {
        return cdn;
    }

    public void setCdn(Cdn cdn) {
        this.cdn = cdn;
    }

    private static class DisplayOptions {

        /**
         * Skip relay-related entities
         */
        private boolean skipRelay = true;

        /**
         * Skip deprecated fields and entities that contain only deprecated fields
         */
        private boolean skipDeprecated = true;

        /**
         * Name of the type to be used as a root
         */
        private String rootType = "Query";

        /**
         * Sort fields on graph by alphabet
         */
        private boolean sortByAlphabet = false;

        /**
         * Show all scalars and enums
         */
        private boolean showLeafFields = true;

        /**
         * Hide the root type
         */
        private boolean hideRoot = false;

        public boolean isSkipRelay() {
            return skipRelay;
        }

        public void setSkipRelay(boolean skipRelay) {
            this.skipRelay = skipRelay;
        }

        public boolean isSkipDeprecated() {
            return skipDeprecated;
        }

        public void setSkipDeprecated(boolean skipDeprecated) {
            this.skipDeprecated = skipDeprecated;
        }

        public String getRootType() {
            return rootType;
        }

        public void setRootType(String rootType) {
            this.rootType = rootType;
        }

        public boolean isSortByAlphabet() {
            return sortByAlphabet;
        }

        public void setSortByAlphabet(boolean sortByAlphabet) {
            this.sortByAlphabet = sortByAlphabet;
        }

        public boolean isShowLeafFields() {
            return showLeafFields;
        }

        public void setShowLeafFields(boolean showLeafFields) {
            this.showLeafFields = showLeafFields;
        }

        public boolean isHideRoot() {
            return hideRoot;
        }

        public void setHideRoot(boolean hideRoot) {
            this.hideRoot = hideRoot;
        }

    }

    public enum Cdn {

        JSDELIVR,
        UNPKG;

        public String getHost() {
            return switch (this) {
                case JSDELIVR -> "https://cdn.jsdelivr.net/npm";
                case UNPKG -> "https://unpkg.com";
            };
        }

    }

}
