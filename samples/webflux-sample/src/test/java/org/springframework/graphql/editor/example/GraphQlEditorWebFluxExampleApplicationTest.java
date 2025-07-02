package org.springframework.graphql.editor.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQlEditorWebFluxExampleApplicationTest {

    @LocalServerPort
    int port;

    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void tearDown() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void testGraphiQlSnapshot() {
        page.navigate("http://localhost:" + port + "/graphiql");

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get("../../screenshots/webflux-graphiql.png"))
        );

        assertThat(page.locator(".graphiql-container")).matchesAriaSnapshot("""
                - button "Show Documentation Explorer":
                  - img "icon"
                - button "Show History"
                - button "Show GraphiQL Explorer"
                - button "Re-fetch GraphQL schema"
                - button "Open short keys dialog"
                - button "Open settings dialog"
                - tablist "Select active operation"
                - button "New tab"
                - link "GraphiQL":
                  - emphasis: i
                - tabpanel:
                  - region "Query Editor":
                    - textbox
                    - text: "query($id: ID!) { artifactRepository(id: $id) { name url } }"
                    - toolbar "Editor Commands":
                      - button "Execute query (Ctrl-Enter)":
                        - img "icon"
                      - button "Prettify query (Shift-Ctrl-P)"
                      - button "Merge fragments into query (Shift-Ctrl-M)"
                      - button "Copy query (Shift-Ctrl-C)"
                  - button "Variables"
                  - button "Headers"
                  - button "Hide editor tools"
                  - region "Variables":
                    - textbox
                    - text: "{ \\"id\\": \\"1\\" }"
                  - region "Result Window":
                    - textbox
                """);
    }

    @Test
    public void testVoyagerSnapshot() {
        page.navigate("http://localhost:" + port + "/voyager");

        Locator locator = page.locator(".graphql-voyager");
        locator.screenshot(
                new Locator.ScreenshotOptions()
                        .setPath(Paths.get("../../screenshots/webflux-voyager.png"))
        );

        assertThat(locator).matchesAriaSnapshot("""
                - link "GraphQL Voyager":
                  - paragraph: GraphQL
                  - paragraph: Voyager
                - button "Change Schema"
                - text: Type List
                - textbox "Search Schema..."
                - text: Query root
                - button:
                  - img
                - paragraph: No Description
                - text: ArtifactRepository
                - button:
                  - img
                - paragraph: No Description
                - text: Project
                - button:
                  - img
                - paragraph: No Description
                - text: Release
                - button:
                  - img
                - paragraph: No Description
                - paragraph:
                  - text: 🛰 Powered by
                  - link "GraphQL Voyager"
                - img: \
                Query greeting String artifactRepositories [ ArtifactRepository ] artifactRepository ArtifactRepository project Project \
                ArtifactRepository id ID ! name String ! url String ! snapshotsEnabled Boolean \
                Project slug ID ! name String ! repositoryUrl String ! status ProjectStatus ! releases [ Release ] \
                Release version String ! status ReleaseStatus ! current Boolean
                """);
    }

}