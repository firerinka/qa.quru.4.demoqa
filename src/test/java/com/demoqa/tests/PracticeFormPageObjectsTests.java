package com.demoqa.tests;

import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.pages.components.ResultsModalComponent;
import com.demoqa.utils.TestDataGenerationUtils;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.demoqa.utils.TestDataGenerationUtils.hobbiesGeneration;
import static com.demoqa.utils.TestDataGenerationUtils.subjectsGeneration;

public class PracticeFormPageObjectsTests extends TestBase {
    Faker faker = new Faker(new Locale("en"));

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    ResultsModalComponent resultsModalComponent = new ResultsModalComponent();

    String firstName,
            lastName,
            email,
            gender,
            phone,
            picturePath,
            currentAddress,
            state,
            city;
    String[] subjects,
            hobbies;
    LocalDate birthDay;

    @BeforeEach
    void testDataGeneration() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        gender = TestDataGenerationUtils.genderGeneration();
        phone = faker.phoneNumber().subscriberNumber(10);
        birthDay = LocalDate.ofInstant(faker.date().birthday(18, 100).toInstant(), ZoneId.systemDefault());
        currentAddress = faker.address().fullAddress();
        state = "Haryana";
        city = "Karnal";
        subjects = subjectsGeneration();
        hobbies = hobbiesGeneration();
        picturePath = "img/testPicture.jpg";
    }

    @Test
    void studentRegistrationFormTest() {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phone)
                .setBirthDate(birthDay)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .uploadPicture(picturePath)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitForm();

        resultsModalComponent.checkModalFormAppeared()
                .checkResult("Student Name", String.format("%s %s", firstName, lastName))
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", birthDay.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy")))
                .checkResult("Subjects", String.join(", ", subjects))
                .checkResult("Hobbies", String.join(", ", hobbies))
                .checkResult("Picture", picturePath.substring(picturePath.lastIndexOf("/") + 1))
                .checkResult("Address", currentAddress)
                .checkResult("State and City", String.format("%s %s", state, city));
    }
}
