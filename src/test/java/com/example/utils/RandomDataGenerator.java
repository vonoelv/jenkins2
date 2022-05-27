package com.example.utils;

import com.example.utils.enums.*;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class RandomDataGenerator {
    Faker faker = new Faker();
    Date from = new Date("1900/01/01");
    Date to = new Date("2100/12/31");
    Date date = faker.date().between(from, to);
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getGender() {
        return Genders.getRandomDisplayName();
    }

    public String getPhoneNumber() {
        return faker.numerify("##########");
    }

    public String getDay() {
        return String.format("%02d", localDate.getDayOfMonth());
    }

    public String getMonth() {
        String month = String.valueOf(localDate.getMonth());
        return month.charAt(0) + month.substring(1).toLowerCase();
    }

    public String getYear() {
        return String.valueOf(localDate.getYear());
    }

    public String getSubject() {
        return Subjects.getRandomDisplayName();
    }

    public String getHobby() {
        return Hobbies.getRandomDisplayName();
    }

    public String getAddress() {
        return faker.address().fullAddress();
    }

    public String getState() {
        return States.getRandomDisplayName();
    }

    public String getCity(String state) {
        String result = null;
        switch (state) {
            case "NCR":
                result = NCR.getRandomDisplayName();
                break;
            case "Haryana":
                result = Haryana.getRandomDisplayName();
                break;
            case "Rajasthan":
                result = Rajasthan.getRandomDisplayName();
                break;
        }
        return result;
    }
}
