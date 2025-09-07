package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

public class PracticeFormRandom extends TestBase1 {

    private final PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test
    void fillFormTest() {
        String firstName = TestData.getFirstName();
        String lastName = TestData.getLastName();
        String email = TestData.getEmail();
        String gender = TestData.getGender();
        String phoneNumber = TestData.getPhoneNumber();
        String day = TestData.getDay();
        String month = TestData.getMonth();
        String year = TestData.getYear();
        String address = TestData.getAddress();

        practiceFormPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setBirthDate(day, month, year)
                .setSubject(TestData.SUBJECT_1)
                .setSubject(TestData.SUBJECT_2)
                .setHobby(TestData.HOBBY_1)
                .setHobby(TestData.HOBBY_2)
                .uploadPicture(TestData.PICTURE)
                .setCurrentAddress(address)
                .setStateAndCity(TestData.STATE, TestData.CITY)
                .submit()
                .verifyModalAppears();

        practiceFormPage.getResultsTable("Student Name", firstName + " " + lastName);
        practiceFormPage.getResultsTable("Student Email", email);
        practiceFormPage.getResultsTable("Gender", gender);
        practiceFormPage.getResultsTable("Mobile", phoneNumber);
        practiceFormPage.getResultsTable("Date of Birth", TestData.getExpectedDateOfBirth(day, month, year));
        practiceFormPage.getResultsTable("Subjects", "Maths, Arts");
        practiceFormPage.getResultsTable("Hobbies", "Sports, Reading");
        practiceFormPage.getResultsTable("Picture", TestData.PICTURE);
        practiceFormPage.getResultsTable("Address", address);
        practiceFormPage.getResultsTable("State and City", TestData.STATE + " " + TestData.CITY);
    }

    @Test
    void fillFormWithMinimumDataTest() {
        String firstName = TestData.getFirstName();
        String lastName = TestData.getLastName();
        String gender = "Male";
        String phoneNumber = TestData.getPhoneNumber();

        practiceFormPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submit()
                .verifyModalAppears();

        practiceFormPage.getResultsTable("Student Name", firstName + " " + lastName);
        practiceFormPage.getResultsTable("Gender", gender);
        practiceFormPage.getResultsTable("Mobile", phoneNumber);
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
        String invalidEmail = "invalid-email";
        String firstName = TestData.getFirstName();
        String lastName = TestData.getLastName();
        String gender = TestData.getGender();
        String phoneNumber = TestData.getPhoneNumber();

        practiceFormPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(invalidEmail)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submit()
                .verifyValidationError("email");
    }
}