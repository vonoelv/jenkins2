package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.example.pages.components.CalendarComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    //Selectors
    SelenideElement formNameField = $(".practice-form-wrapper");
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement userEmailInput = $("#userEmail");
    SelenideElement genderRadioButtons = $("#genterWrapper");
    SelenideElement userNumberInput = $("#userNumber");
    SelenideElement dateOfBirthDropdown = $("#dateOfBirthInput");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement hobbiesInput = $("#hobbiesWrapper");
    SelenideElement uploadPictureButton = $("#uploadPicture");
    SelenideElement currentAddressInput = $("#currentAddress");
    SelenideElement stateComboBox = $("#state");
    SelenideElement cityComboBox = $("#city");
    SelenideElement submitButton = $("#submit");
    SelenideElement resultTableNameField = $("#example-modal-sizes-title-lg");
    SelenideElement resultTable = $(".table-responsive");

    String formHeaderText = "Student Registration Form";
    String resultTableHeaderText = "Thanks for submitting the form";
    String resultTableColumnHeaders = "Label\tValues";

    //actions
    @Step("Open registration form")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        formNameField.shouldHave(text(formHeaderText));
        return this;
    }

    @Step("Set first name: {value}")
    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Set last name: {value}")
    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Set email: {value}")
    public RegistrationFormPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Set gender: {value}")
    public RegistrationFormPage setGender(String value) {
        genderRadioButtons.$(byText(value)).click();
        return this;
    }

    @Step("Set user number: {value}")
    public RegistrationFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Set date of birth: {day}/{month}/{year}")
    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthDropdown.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Set subject: {value}")
    public RegistrationFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Set hobby: {value}")
    public RegistrationFormPage setHobby(String value) {
        hobbiesInput.$(byText(value)).click();
        return this;
    }

    @Step("Upload picture: {value}")
    public RegistrationFormPage uploadPicture(String value) {
        uploadPictureButton.uploadFromClasspath(value);
        return this;
    }

    @Step("Set address: {value}")
    public RegistrationFormPage setAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    @Step("Set state: {value}")
    public RegistrationFormPage setState(String value) {
        stateComboBox.click();
        $(byText(value)).click();
        return this;
    }

    @Step("Set city: {value}")
    public RegistrationFormPage setCity(String value) {
        cityComboBox.click();
        $(byText(value)).click();
        return this;
    }

    @Step("Click submit")
    public RegistrationFormPage clickSubmit() {
        submitButton.click();
        return this;
    }

    @Step("Check result table headers")
    public RegistrationFormPage checkResultTableHeaders() {
        resultTableNameField.shouldHave(text(resultTableHeaderText));
        resultTable.shouldHave(text(resultTableColumnHeaders));
        return this;
    }

    @Step("Check result table field: {key}={value}")
    public RegistrationFormPage checkResultTableField(String key, String value) {
        resultTable.shouldHave(text(key + "\t" + value));
        return this;
    }

}
