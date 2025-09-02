package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private final SelenideElement table = $(".table-responsive");

    public ResultsTableComponent verifyResults(String... values) {
        for (String value : values) {
            table.shouldHave(text(value));
        }
        return this;
    }

    public void checkResult(String key, String value) {
        table.$(byText(key))
                .parent()
                .shouldHave(text(value));
    }
}