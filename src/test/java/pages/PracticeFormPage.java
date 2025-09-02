package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;
import pages.components.StateCityComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            currentAddressInput = $("#currentAddress"),
            subjectsInput = $("#subjectsInput"),
            uploadPictureInput = $("#uploadPicture"),
            submitButton = $("#submit"),
            modalTitle = $(".modal-title");

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final StateCityComponent stateCityComponent = new StateCityComponent();
    private final ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public PracticeFormPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public PracticeFormPage setGender(String value) {
        $(byText(value)).click();
        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public PracticeFormPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public PracticeFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public PracticeFormPage setHobby(String value) {
        $(byText(value)).click();
        return this;
    }

    public PracticeFormPage uploadPicture(String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public PracticeFormPage setStateAndCity(String state, String city) {
        stateCityComponent.setStateAndCity(state, city);
        return this;
    }

    public PracticeFormPage submit() {
        submitButton.click();
        return this;
    }

    public PracticeFormPage verifyModalAppears() {
        modalTitle.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public ResultsTableComponent getResultsTable() {
        return resultsTableComponent;
    }

    public PracticeFormPage verifyValidationError(String fieldName) {
        modalTitle.shouldNotBe(visible);

        // Дополнительные проверки для разных полей
        switch (fieldName.toLowerCase()) {
            case "first name":
                firstNameInput.shouldBe(empty);
                break;
            case "last name":
                lastNameInput.shouldBe(empty);
                break;
            case "gender":

                $("input[name='gender']:checked").shouldNotBe(exist);
                break;
            case "mobile":
                userNumberInput.shouldBe(empty);
                break;
            case "email":

                userEmailInput.shouldHave(value("invalid-email"));
                break;
        }
        return this;

    }
}