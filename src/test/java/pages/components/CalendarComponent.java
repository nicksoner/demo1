package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement
            dateInput = $("#dateOfBirthInput"),
            monthSelect = $(".react-datepicker__month-select"),
            yearSelect = $(".react-datepicker__year-select"),
            dayPicker = $(".react-datepicker__month");

    public void setDate(String day, String month, String year) {
        dateInput.click();

        monthSelect.selectOption(month);
        yearSelect.selectOption(year);

        dayPicker.$(byText(day)).click();
    }
}