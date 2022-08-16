package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.pages.components.ResultsModalComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PracticeFormPageObjectsTests {
    static final String FIRST_NAME = "Anna",
            LAST_NAME = "Petrova",
            EMAIL = "anna.petrova@gmail.com",
            GENDER = "Female",
            PHONE = "9992223344",
            BIRTH_YEAR = "2000",
            BIRTH_MONTH = "September",
            BIRTH_DAY = "30",
            PICTURE_PATH = "img/testPicture.jpg",
            CURRENT_ADDRESS = "Private road 1",
            STATE = "Haryana",
            CITY = "Karnal";
    static final String[] SUBJECTS = {"Maths", "Arts"},
            HOBBIES = {"Reading", "Music"};

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
