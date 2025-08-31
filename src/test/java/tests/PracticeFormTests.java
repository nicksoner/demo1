package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

public class PracticeFormTests extends TestBase1 {

    private final PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test
    void fillFormTest() {
        practiceFormPage.openPage()
                .setFirstName("Николай")
                .setLastName("Харчук")
                .setEmail("example@example.com")
                .setGender("Male")
                .setUserNumber("1234567899")
                .setBirthDate("26", "October", "1992")
                .setSubject("Math")
                .setSubject("Arts")
                .setHobby("Sports")
                .setHobby("Reading")
                .uploadPicture("cat_picture.jpg")
                .setCurrentAddress("Новиград")
                .setStateAndCity("NCR", "Delhi")
                .submit()
                .verifyModalAppears();

        practiceFormPage.getResultsTable()
                .verifyResults(
                        "Николай Харчук",
                        "example@example.com",
                        "Male",
                        "26 October,1992",
                        "Maths, Arts",
                        "Sports, Reading",
                        "cat_picture.jpg",
                        "Новиград",
                        "NCR Delhi"
                );
    }

    @Test
    void fillFormWithMinimumDataTest() {
        practiceFormPage.openPage()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setGender("Male")
                .setUserNumber("1234567890")
                .submit()
                .verifyModalAppears();

        practiceFormPage.getResultsTable()
                .verifyResults(
                        "Иван Иванов",
                        "Male",
                        "1234567890"
                );
    }

    @Test
    void negativeTestWithoutRequiredFields() {
        practiceFormPage.openPage()
                .submit();

        practiceFormPage.verifyValidationError("first name")
                .verifyValidationError("last name")
                .verifyValidationError("gender")
                .verifyValidationError("mobile");
    }

    @Test
    void negativeTestWithInvalidEmail() {
        practiceFormPage.openPage()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setEmail("invalid-email")
                .setGender("Male")
                .setUserNumber("1234567890")
                .submit()
                .verifyValidationError("email");
    }
}
