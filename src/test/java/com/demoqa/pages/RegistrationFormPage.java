package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import io.qameta.allure.Step;

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

    @Step("Открываем страницу с формой automation-practice-form")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    @Step("Заполняем First Name значением '{firstName}'")
    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Заполняем Last Name значением '{lastName}'")
    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Заполняем Email значением '{userEmail}'")
    public RegistrationFormPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    @Step("Устанавливаем Gender в значение '{gender}'")
    public RegistrationFormPage setGender(String gender) {
        genderRadioButton.$(byText(gender)).click();
        return this;
    }

    @Step("Заполняем Mobile Number значением '{userNumber}'")
    public RegistrationFormPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    @Step("Заполняем Date of Birth значением '{birthDay}'")
    public RegistrationFormPage setBirthDate(LocalDate birthDay) {
        birthDateInput.click();
        calendarComponent.setDate(birthDay);
        return this;
    }

    @Step("Заполняем Subjects значениями '{subjects}'")
    public RegistrationFormPage setSubjects(String[] subjects) {
        for (String subject : subjects) {
            subjectsInput.setValue(subject).pressEnter();
        }
        return this;
    }

    @Step("Выбираем значения Hobbies '{hobbies}'")
    public RegistrationFormPage setHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            hobbiesInput.$(byText(hobby)).click();
        }
        return this;
    }

    @Step("Загружаем в Picture изображение '{picturePath}'")
    public RegistrationFormPage uploadPicture(String picturePath) {
        pictureUploader.uploadFromClasspath(picturePath);
        return this;
    }

    @Step("Заполняем Current Address значением '{currentAddress}'")
    public RegistrationFormPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    @Step("Для State and City выбираем значение State '{state}'")
    public RegistrationFormPage setState(String state) {
        stateSelector.click();
        stateSelector.$(byText(state)).click();
        return this;
    }

    @Step("Для State and City выбираем значение City '{city}'")
    public RegistrationFormPage setCity(String city) {
        citySelector.click();
        citySelector.$(byText(city)).click();
        return this;
    }

    @Step("Нажимаем на кнопку Submit")
    public void submitForm() {
        submitButton.click();
    }

}
