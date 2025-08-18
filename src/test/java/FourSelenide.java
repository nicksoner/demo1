import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FourSelenide {
    private final String codeExample = """
            @ExtendWith({SoftAssertsExtension.class})
            class Tests {
              @Test
              void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");

                $("#first").should(visible).click();
                $("#second").should(visible).click();
              }
            }""";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

        @Test
        void exampleCodeJUnit5() {
            open("/selenide/selenide");
            $("#wiki-tab").click();
            $("#wiki-body").$("ul").shouldHave(text("Soft assertions"));
            $("#wiki-body")
                    .$("a[href*='SoftAssertions']")
                    .shouldBe(visible)
                    .click();
            $("#wiki-body").shouldHave(text("3. Using JUnit5 extend test class:"));
            $("#repo-content-pjax-container").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})"));
            $("#repo-content-pjax-container").shouldHave(text(codeExample));
        }
    }

