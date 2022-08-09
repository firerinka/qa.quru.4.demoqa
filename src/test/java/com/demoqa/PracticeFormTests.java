package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    static final String FIRST_NAME = "Anna";
    static final String LAST_NAME = "Petrova";
    static final String EMAIL = "anna.petrova@gmail.com";

    static final String GENDER = "Female";
    static final String PHONE = "9992223344";
    static final String BIRTH_YEAR = "2000";
    static final String BIRTH_MONTH = "September";
    static final String BIRTH_DAY = "15";
    static final String SUBJECT = "Maths";
    static final String HOBBY = "Reading";
    static final String PICTURE_PATH = "src/test/resources/testPicture.jpg";
    static final String CURRENT_ADDRESS = "Private road 1";
    static final String STATE = "Haryana";
    static final String CITY = "Karnal";

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "760x840";
    }

    @Test
    void studentRegistrationFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(EMAIL);
        $("#genterWrapper").$(byText(GENDER)).click();
        $("#userNumber").setValue(PHONE);

        $("#dateOfBirthInput").click();
        $("[class=\"react-datepicker__year-select\"]").selectOption(BIRTH_YEAR);
        $("[class=\"react-datepicker__month-select\"]").selectOption(BIRTH_MONTH);
        $("[class~=\"react-datepicker__day--0" + BIRTH_DAY + "\"]").click();

        $("#subjectsInput").setValue(SUBJECT).pressEnter();
        $("#hobbiesWrapper").$(byText(HOBBY)).click();
        $("#uploadPicture").uploadFile(new File(PICTURE_PATH));
        $("#currentAddress").setValue(CURRENT_ADDRESS);

        SelenideElement stateElement = $("#stateCity-wrapper").$("#state");
        stateElement.click();
        stateElement.$(byText(STATE)).click();

        SelenideElement cityElement = $("#stateCity-wrapper").$("#city");
        cityElement.click();
        cityElement.$(byText(CITY)).click();

        $("#submit").pressEnter();

        checkResult("Student Name", FIRST_NAME + " " + LAST_NAME);
        checkResult("Student Email", EMAIL);
        checkResult("Gender", GENDER);
        checkResult("Mobile", PHONE);
        checkResult("Date of Birth", BIRTH_DAY + " " + BIRTH_MONTH + "," + BIRTH_YEAR);
        checkResult("Subjects", SUBJECT);
        checkResult("Hobbies", HOBBY);
        checkResult("Picture", PICTURE_PATH.substring(PICTURE_PATH.lastIndexOf("/") + 1));
        checkResult("Address", CURRENT_ADDRESS);
        checkResult("State and City", STATE + " " + CITY);
    }

    public void checkResult(String key, String value) {
        $(".table-responsive").$(byText(key))
                .parent().shouldHave(text(value));
    }
}
