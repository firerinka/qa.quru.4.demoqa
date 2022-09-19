package com.demoqa.pages;

import com.demoqa.elements.*;
import com.demoqa.pages.components.CalendarComponent;
import io.qameta.allure.Step;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {
    private static final String FORM_TITLE = "Student Registration Form";
    private CalendarComponent calendarComponent = new CalendarComponent();
    private Input firstNameInput = new Input("firstName", $("#firstName"));
    private Input lastNameInput = new Input("lastName", $("#lastName"));
    private Input userEmailInput = new Input("userEmail", $("#userEmail"));
    private Input userNumberInput = new Input("userNumber", $("#userNumber"));
    private Input birthDateInput = new Input("dateOfBirth", $("#dateOfBirthInput"));
    private Input subjectsInput = new Input("subjects", $("#subjectsInput"));
    private Input currentAddressInput = new Input("currentAddress", $("#currentAddress"));
    private RadioButton genderRadioButton = new RadioButton("gender", $("#genterWrapper"));
    private Checkbox hobbiesCheckboxes = new Checkbox("hobbies", $("#hobbiesWrapper"));
    private Button submitButton = new Button("submit", $("#submit"));
    private PictureUploader pictureUploader = new PictureUploader("picture", $("#uploadPicture"));
    private Select stateSelect = new Select("state", $("#stateCity-wrapper").$("#state"));
    private Select citySelect = new Select("city", $("#stateCity-wrapper").$("#city"));

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
        firstNameInput.setInputValue(firstName);
        return this;
    }

    @Step("Заполняем Last Name значением '{lastName}'")
    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setInputValue(lastName);
        return this;
    }

    @Step("Заполняем Email значением '{userEmail}'")
    public RegistrationFormPage setUserEmail(String userEmail) {
        userEmailInput.setInputValue(userEmail);
        return this;
    }

    @Step("Устанавливаем Gender в значение '{gender}'")
    public RegistrationFormPage setGender(String gender) {
        genderRadioButton.setByLabelName(gender);
        return this;
    }

    @Step("Заполняем Mobile Number значением '{userNumber}'")
    public RegistrationFormPage setUserNumber(String userNumber) {
        userNumberInput.setInputValue(userNumber);
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
            subjectsInput.selectFromAutocompleteByFullValue(subject);
        }
        return this;
    }

    @Step("Выбираем значения Hobbies '{hobbies}'")
    public RegistrationFormPage setHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            hobbiesCheckboxes.selectBoxByTitle(hobby);
        }
        return this;
    }

    @Step("Загружаем в Picture изображение '{picturePath}'")
    public RegistrationFormPage uploadPicture(String picturePath) {
        pictureUploader.uploadPicture(picturePath);
        return this;
    }

    @Step("Заполняем Current Address значением '{currentAddress}'")
    public RegistrationFormPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setInputValue(currentAddress);
        return this;
    }

    @Step("Для State and City выбираем значение State '{state}'")
    public RegistrationFormPage setState(String state) {
        stateSelect.selectValue(state);
        return this;
    }

    @Step("Для State and City выбираем значение City '{city}'")
    public RegistrationFormPage setCity(String city) {
        citySelect.selectValue(city);
        return this;
    }

    @Step("Загружаем форму")
    public void submitForm() {
        submitButton.click();
    }

}
