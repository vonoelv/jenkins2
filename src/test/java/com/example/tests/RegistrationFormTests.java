package com.example.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {
    String firstName = "Max",
            lastName = "Maxov",
            userEmail = "mmaxov@example.com",
            gender = "Other",
            userNumber = "1234567890",
            dayOfBirth = "02",
            monthOfBirth = "September",
            yearOfBirth = "2008",
            dateOfBirth = String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth),
            subjects = "Physics",
            hobbies = "Reading",
            picturePath = "images/",
            pictureName = "fakeimage.png",
            currentAddress = "Some street 1",
            state = "Haryana",
            city = "Panipat";


    @BeforeAll
    static void initialization() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1200x1080";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove");
        executeJavaScript("$('#fixedban').remove");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth + ":not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        $("#uploadPicture").uploadFromClasspath(picturePath + pictureName);
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#city").click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Student Name\t" + firstName + " " + lastName),
                text("Student Email\t" + userEmail),
                text("Gender\t" + gender),
                text("Mobile\t" + userNumber),
                text("Date of Birth\t" + dateOfBirth),
                text("Subjects\t" + subjects),
                text("Hobbies\t" + hobbies),
                text("Picture\t" + pictureName),
                text("Address\t" + currentAddress),
                text("State and City\t" + state + " " + city)
        );
    }
}
