package com.demoqa.pages.components;

import com.demoqa.elements.Element;
import com.demoqa.elements.NativeSelect;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private NativeSelect yearSelect = new NativeSelect("year", $(".react-datepicker__year-select"));
    private NativeSelect monthSelect = new NativeSelect("month", $(".react-datepicker__month-select"));
    private Element dayElement;

    @Step("Выбираем дату {date} в CalendarComponent")
    public CalendarComponent setDate(LocalDate date) {
        yearSelect.selectValue(String.valueOf(date.getYear()));
        monthSelect.selectValue(date.format(DateTimeFormatter.ofPattern("MMMM")));

        dayElement =  new Element("day of month", $(".react-datepicker__day--" +
                date.format(DateTimeFormatter.ofPattern("0dd")) + ":not(.react-datepicker__day--outside-month)"));
        dayElement.click();
        return this;
    }
}
