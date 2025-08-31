package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends TestBase1 {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillTextBoxFormTest() {
        textBoxPage.openPage()
                .setUserName("John Doe")
                .setUserEmail("john@example.com")
                .setCurrentAddress("123 Main St")
                .setPermanentAddress("456 Secondary St")
                .submit()
                .verifyOutputContains("John Doe")
                .verifyOutputContains("john@example.com")
                .verifyOutputContains("123 Main St")
                .verifyOutputContains("456 Secondary St");
    }
}
