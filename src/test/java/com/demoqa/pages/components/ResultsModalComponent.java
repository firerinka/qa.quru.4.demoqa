package com.demoqa.pages.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsModalComponent {

    private static final String MODAL_TITLE = "Thanks for submitting the form";

    @Step("Проверяем появление окна с результатами")
    public ResultsModalComponent checkModalFormAppeared() {
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text(MODAL_TITLE));
        return this;
    }

    @Step("Проверяем, что Label '{label}' имеет Values '{values}'")
    public ResultsModalComponent checkResult(String label, String values) {
        $(".table-responsive").$(byText(label))
                .parent().shouldHave(text(values));
        return this;
    }
}
