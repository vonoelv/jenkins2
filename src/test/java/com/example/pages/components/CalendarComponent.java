package com.example.pages.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    SelenideElement monthComboBox = $(".react-datepicker__month-select");
    SelenideElement yearComboBox = $(".react-datepicker__year-select");

    public void setDate(String day, String month, String year) {
        monthComboBox.selectOption(month);
        yearComboBox.selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}
