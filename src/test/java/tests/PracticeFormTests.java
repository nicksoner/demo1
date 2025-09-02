package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

public class PracticeFormTests extends TestBase1 {

    private final PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test
    void fillFormTest() {
        practiceFormPage.openPage()
                .removeBanner()
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

        practiceFormPage.getResultsTable().checkResult("Student Name", "Николай Харчук");
        practiceFormPage.getResultsTable().checkResult("Student Email", "example@example.com");
        practiceFormPage.getResultsTable().checkResult("Gender", "Male");
        practiceFormPage.getResultsTable().checkResult("Mobile", "1234567899");
        practiceFormPage.getResultsTable().checkResult("Date of Birth", "26 October,1992");
        practiceFormPage.getResultsTable().checkResult("Subjects", "Maths, Arts");
        practiceFormPage.getResultsTable().checkResult("Hobbies", "Sports, Reading");
        practiceFormPage.getResultsTable().checkResult("Picture", "cat_picture.jpg");
        practiceFormPage.getResultsTable().checkResult("Address", "Новиград");
        practiceFormPage.getResultsTable().checkResult("State and City", "NCR Delhi");
    }

    @Test
    void fillFormWithMinimumDataTest() {
        practiceFormPage.openPage()
                .removeBanner()
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
                .removeBanner()
                .submit();

        practiceFormPage.verifyValidationError("first name")
                .verifyValidationError("last name")
                .verifyValidationError("gender")
                .verifyValidationError("mobile");
    }

    @Test
    void negativeTestWithInvalidEmail() {
        practiceFormPage.openPage()
                .removeBanner()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setEmail("invalid-email")
                .setGender("Male")
                .setUserNumber("1234567890")
                .submit()
                .verifyValidationError("email");
    }
}
