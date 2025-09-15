package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.stream.Stream;

@DisplayName("Тестирование формы с тремя типами дата-провайдеров")
public class ParametrizedTests {

    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        js = (JavascriptExecutor) driver;
    }

    @DisplayName("Разные имена с @ValueSource")
    @ParameterizedTest(name = "Имя: {0}")
    @ValueSource(strings = {"Иван", "Анна", "Михаил", "Ольга"})
    void testFormWithDifferentNames(String firstName) {
        driver.get("https://demoqa.com/automation-practice-form");

        removeFooter();

        setFirstName(firstName);
        setLastName("Петров");
        setGender("Male");
        setPhoneNumber("1234567890");

        submitForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        assertTrue(result.isDisplayed(), "Форма должна быть успешно отправлена для имени: " + firstName);
    }

    @DisplayName("Разные данные с @CsvSource")
    @ParameterizedTest(name = "Имя: {0}, Фамилия: {1}, Телефон: {2}")
    @CsvSource({
            "Иван, Иванов, 1234567890",
            "Анна, Петрова, 0987654321",
            "Михаил, Сидоров, 5551234567",
            "Ольга, Смирнова, 9998887777"
    })
    void testFormWithCsvSource(String firstName, String lastName, String phoneNumber) {
        driver.get("https://demoqa.com/automation-practice-form");

        removeFooter();

        setFirstName(firstName);
        setLastName(lastName);
        setGender("Male");
        setPhoneNumber(phoneNumber);

        submitForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        assertTrue(result.isDisplayed(), "Форма должна быть успешно отправлена");
        assertTrue(driver.getPageSource().contains(firstName),
                "Результат должен содержать имя: " + firstName);
    }

    @DisplayName("Комплексные данные с @MethodSource")
    @ParameterizedTest(name = "Тест #{index}: Имя - {0}, Фамилия - {1}")
    @MethodSource("provideFormTestData")
    void testFormWithMethodSource(String firstName, String lastName, String gender) {
        driver.get("https://demoqa.com/automation-practice-form");

        removeFooter();

        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setPhoneNumber("1234567890");

        submitForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        assertTrue(result.isDisplayed(), "Форма должна быть успешно отправлена");
        assertTrue(driver.getPageSource().contains(firstName),
                "Результат должен содержать имя: " + firstName);
    }

    static Stream<Arguments> provideFormTestData() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Male"),
                Arguments.of("Анна", "Петрова", "Female"),
                Arguments.of("Михаил", "Сидоров", "Male"),
                Arguments.of("Ольга", "Смирнова", "Female"),
                Arguments.of("Алекс", "Джонсон", "Other")
        );
    }

    private void setFirstName(String firstName) {
        WebElement element = driver.findElement(By.id("firstName"));
        element.clear();
        element.sendKeys(firstName);
    }

    private void setLastName(String lastName) {
        WebElement element = driver.findElement(By.id("lastName"));
        element.clear();
        element.sendKeys(lastName);
    }

    private void setGender(String gender) {
        String genderSelector = "";
        switch (gender.toLowerCase()) {
            case "male": genderSelector = "label[for='gender-radio-1']"; break;
            case "female": genderSelector = "label[for='gender-radio-2']"; break;
            case "other": genderSelector = "label[for='gender-radio-3']"; break;
        }
        driver.findElement(By.cssSelector(genderSelector)).click();
    }

    private void setPhoneNumber(String phoneNumber) {
        WebElement element = driver.findElement(By.id("userNumber"));
        element.clear();
        element.sendKeys(phoneNumber);
    }

    private void removeFooter() {
        js.executeScript("document.querySelector('footer').style.display='none';");
        js.executeScript("document.querySelector('#fixedban').style.display='none';");
    }

    private void submitForm() {
        WebElement submitButton = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].click();", submitButton);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}