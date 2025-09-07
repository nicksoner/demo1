package tests;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestData {
    private static final Faker faker = new Faker(new Locale("ru"));

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress()
                .toLowerCase() // в нижнем регистре
                .replaceAll("[^a-z0-9@.-]", "");
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

    public static final String SUBJECT_1 = "Math";
    public static final String SUBJECT_2 = "Arts";
    public static final String HOBBY_1 = "Sports";
    public static final String HOBBY_2 = "Reading";
    public static final String PICTURE = "cat_picture.jpg";
    public static final String STATE = "NCR";
    public static final String CITY = "Delhi";

    public static String getExpectedDateOfBirth(String day, String month, String year) {
        return day + " " + month + "," + year;
    }
}