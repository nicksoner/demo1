package tests;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestData {
    private static final Faker faker = new Faker(new Locale("en")); // Изменено на английскую локаль

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getGender() {
        String[] genders = {"Male", "Female", "Other"};
        return faker.options().option(genders);
    }

    public static String getPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getDay() {
        return String.valueOf(faker.number().numberBetween(1, 28));
    }

    public static String getMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return faker.options().option(months);
    }

    public static String getYear() {
        return String.valueOf(faker.number().numberBetween(1950, 2005));
    }

    public static String getAddress() {
        return faker.address().fullAddress();
    }

    // Генерация предметов через Faker
    public static String getSubject() {
        String[] subjects = {"Maths", "Arts", "English", "Physics", "Chemistry", "Computer Science",
                "Economics", "Social Studies", "History", "Civics", "Commerce", "Accounting",
                "Biology", "Hindi"};
        return faker.options().option(subjects);
    }

    // Генерация хобби через Faker
    public static String getHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return faker.options().option(hobbies);
    }

    public static final String PICTURE = "cat_picture.jpg";

    // Генерация штата через Faker
    public static String getState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return faker.options().option(states);
    }

    // Генерация города в зависимости от выбранного штата
    public static String getCity(String state) {
        switch (state) {
            case "NCR":
                String[] ncrCities = {"Delhi", "Gurgaon", "Noida"};
                return faker.options().option(ncrCities);
            case "Uttar Pradesh":
                String[] upCities = {"Agra", "Lucknow", "Merrut"};
                return faker.options().option(upCities);
            case "Haryana":
                String[] haryanaCities = {"Karnal", "Panipat"};
                return faker.options().option(haryanaCities);
            case "Rajasthan":
                String[] rajasthanCities = {"Jaipur", "Jaiselmer"};
                return faker.options().option(rajasthanCities);
            default:
                return "Delhi";
        }
    }

    public static String getExpectedDateOfBirth(String day, String month, String year) {
        return day + " " + month + "," + year;
    }
}