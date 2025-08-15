import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBoxNicksoner {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920х1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        //open browser
        open("/automation-practice-form");

        //fill text forms
        $("#firstName").setValue("Николай");
        $("#lastName").setValue("Харчук");
        $("#userEmail").setValue("example@example.com");
        $("#userNumber").setValue("1234567899");
        $("#currentAddress").setValue("Новиград");

        //gender
        $(byText("Male")).click();

        //birthday
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("1992");
        $(".react-datepicker__day--026:not(.react-datepicker__day--outside-month)").click();

        //subjects
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("Arts").pressEnter();

        //hobbies
        $(byText("Sports")).click();
        $(byText("Reading")).click();

        //file
        $("#uploadPicture").uploadFromClasspath("cat_picture.jpg");

        //state and country
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        //submit
        $("#submit").click();

        //result
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Николай Харчук"),
                text("example@example.com"),
                text("Male"),
                text("26 October,1992"),
                text("Maths, Arts"),
                text("Sports, Reading"),
                text("cat_picture.jpg"),
                text("Новиград"),
                text("NCR Delhi")
        );
    }
}