package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {
    private static final String FORM_TITLE = "Student Registration Form";
    private CalendarComponent calendarComponent = new CalendarComponent();
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderRadioButton = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            birthDateInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureUploader = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateSelector = $("#stateCity-wrapper").$("#state"),
            citySelector = $("#stateCity-wrapper").$("#city"),
            submitButton = $("#submit");

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationFormPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        genderRadioButton.$(byText(gender)).click();
        return this;
    }

    public RegistrationFormPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    public RegistrationFormPage setBirthDate(LocalDate birthDay) {
        birthDateInput.click();
        calendarComponent.setDate(birthDay);
        return this;
    }

    public RegistrationFormPage setSubjects(String[] subjects) {
        for (String subject : subjects) {
            subjectsInput.setValue(subject).pressEnter();
        }
        return this;
    }

    public RegistrationFormPage setHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            hobbiesInput.$(byText(hobby)).click();
        }
        return this;
    }

    public RegistrationFormPage uploadPicture(String picturePath) {
        pictureUploader.uploadFromClasspath(picturePath);
        return this;
    }

    public RegistrationFormPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public RegistrationFormPage setState(String state) {
        stateSelector.click();
        stateSelector.$(byText(state)).click();
        return this;
    }

    public RegistrationFormPage setCity(String city) {
        citySelector.click();
        citySelector.$(byText(city)).click();
        return this;
    }

    public void submitForm() {
        submitButton.click();
    }

}
