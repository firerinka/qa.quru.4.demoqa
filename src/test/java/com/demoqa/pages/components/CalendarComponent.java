package com.demoqa.pages.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public CalendarComponent setDate(LocalDate date) {
        $(".react-datepicker__year-select").selectOption(String.valueOf(date.getYear()));
        $(".react-datepicker__month-select")
                .selectOption(date.format(DateTimeFormatter.ofPattern("MMMM")));
        $(".react-datepicker__day--" +
                date.format(DateTimeFormatter.ofPattern("0dd")) + ":not(.react-datepicker__day--outside-month)").click();
        return this;
    }
}
