package org.springframework.graphql.editor.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQlEditorWebMvcExampleApplicationTest {

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

        assertThat(page.getByTestId("graphiql-container")).matchesAriaSnapshot("""
                - button "Show Documentation Explorer":
                  - img "docs icon"
                - button "Show History"
                - button "Show GraphiQL Explorer"
                - button "Re-fetch GraphQL schema"
                - button "Open short keys dialog"
                - button "Open settings dialog"
                - tablist "Select active operation"
                - button "Add tab"
                - link "GraphiQL":
                  - emphasis: i
                - tabpanel:
                  - region "Query Editor":
                    - textbox
                    - text: "query($id: ID!) { artifactRepository(id: $id) { name url } }"
                    - toolbar "Editor Commands":
                      - button "Execute query (Ctrl-Enter)":
                        - img "play icon"
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

}