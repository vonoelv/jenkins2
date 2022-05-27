package com.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.CredentialsConfig;
import com.example.helpers.Attach;
import com.example.pages.RegistrationFormPage;
import com.example.utils.RandomDataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

@Tag("Jenkins")
public class RegistrationFormWithPageObjectsTests {
    RandomDataGenerator generator = new RandomDataGenerator();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstName = generator.getFirstName(),
            lastName = generator.getLastName(),
            userEmail = generator.getEmail(),
            gender = generator.getGender(),
            userNumber = generator.getPhoneNumber(),
            dayOfBirth = generator.getDay(),
            monthOfBirth = generator.getMonth(),
            yearOfBirth = generator.getYear(),
            dateOfBirth = format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth),
            subject = generator.getSubject(),
            hobby = generator.getHobby(),
            picturePath = "images/",
            pictureName = "fakeimage.png",
            currentAddress = generator.getAddress(),
            state = generator.getState(),
            city = generator.getCity(state);

    String resultTableName = "Student Name",
            resultTableEmail = "Student Email",
            resultTableGender = "Gender",
            resultTableMobile = "Mobile",
            resultTableDateOfBirth = "Date of Birth",
            resultTableSubjects = "Subjects",
            resultTableHobbies = "Hobbies",
            resultTablePicture = "Picture",
            resultTableAddress = "Address",
            resultTableStateAndCity = "State and City";

    @BeforeAll
    static void setUp() {
        CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);
        Configuration.remote = format("https://%s:%s@%s/wd/hub",
                credentialsConfig.login(), credentialsConfig.password(), System.getProperty("selenoidRemote"));
        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.browserSize = System.getProperty("browserSize", "1200x1080");

        System.out.println(Configuration.remote);
        System.out.println(Configuration.baseUrl);
        System.out.println(Configuration.browserSize);

        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    @Test
    @DisplayName("Fill registration test")
    void fillFormTest() {
        registrationFormPage.openPage();

        step("Fill registration form", () ->
                registrationFormPage
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(userEmail)
                        .setGender(gender)
                        .setUserNumber(userNumber)
                        .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                        .setSubject(subject)
                        .setHobby(hobby)
                        .uploadPicture(picturePath + pictureName)
                        .setAddress(currentAddress)
                        .setState(state)
                        .setCity(city)
                        .clickSubmit()
        );

        step("Verify the result form", () ->
                registrationFormPage
                        .checkResultTableHeaders()
                        .checkResultTableField(resultTableName, firstName + " " + lastName)
                        .checkResultTableField(resultTableEmail, userEmail)
                        .checkResultTableField(resultTableGender, gender)
                        .checkResultTableField(resultTableMobile, userNumber)
                        .checkResultTableField(resultTableDateOfBirth, dateOfBirth)
                        .checkResultTableField(resultTableSubjects, subject)
                        .checkResultTableField(resultTableHobbies, hobby)
                        .checkResultTableField(resultTablePicture, pictureName)
                        .checkResultTableField(resultTableAddress, currentAddress)
                        .checkResultTableField(resultTableStateAndCity, state + " " + city)
        );
    }

}
