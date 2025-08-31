package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private final SelenideElement table = $(".table-responsive");

    public ResultsTableComponent checkResult(String key, String value) {
        table.$(String.format("td:contains(%s)", key))
                .sibling(0)
                .shouldHave(text(value));
        return this;
    }

    public ResultsTableComponent verifyResults(String... values) {
        for (String value : values) {
            table.shouldHave(text(value));
        }
        return this;
    }
}