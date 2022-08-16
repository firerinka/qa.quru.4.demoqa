package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.pages.components.ResultsModalComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PracticeFormPageObjectsTests {
    static final String FIRST_NAME = "Anna";
    static final String LAST_NAME = "Petrova";
    static final String EMAIL = "anna.petrova@gmail.com";

    static final String GENDER = "Female";
    static final String PHONE = "9992223344";
    static final String BIRTH_YEAR = "2000";
    static final String BIRTH_MONTH = "September";
    static final String BIRTH_DAY = "30";
    static final String[] SUBJECTS = {"Maths", "Arts"};
    static final String[] HOBBIES = {"Reading", "Music"};
    static final String PICTURE_PATH = "img/testPicture.jpg";
    static final String CURRENT_ADDRESS = "Private road 1";
    static final String STATE = "Haryana";
    static final String CITY = "Karnal";

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    ResultsModalComponent resultsModalComponent = new ResultsModalComponent();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void studentRegistrationFormTest() {
        registrationFormPage.openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setUserEmail(EMAIL)
                .setGender(GENDER)
                .setUserNumber(PHONE)
                .setBirthDate(BIRTH_DAY, BIRTH_MONTH, BIRTH_YEAR)
                .setSubjects(SUBJECTS)
                .setHobbies(HOBBIES)
                .uploadPicture(PICTURE_PATH)
                .setCurrentAddress(CURRENT_ADDRESS)
                .setState(STATE)
                .setCity(CITY)
                .submitForm();

        resultsModalComponent.checkModalFormAppeared()
                .checkResult("Student Name", FIRST_NAME + " " + LAST_NAME)
                .checkResult("Student Email", EMAIL)
                .checkResult("Gender", GENDER)
                .checkResult("Mobile", PHONE)
                .checkResult("Date of Birth", BIRTH_DAY + " " + BIRTH_MONTH + "," + BIRTH_YEAR)
                .checkResult("Subjects", String.join(", ", SUBJECTS))
                .checkResult("Hobbies", String.join(", ", HOBBIES))
                .checkResult("Picture", PICTURE_PATH.substring(PICTURE_PATH.lastIndexOf("/") + 1))
                .checkResult("Address", CURRENT_ADDRESS)
                .checkResult("State and City", STATE + " " + CITY);
    }
}
