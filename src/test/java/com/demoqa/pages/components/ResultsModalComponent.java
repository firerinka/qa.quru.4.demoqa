package com.demoqa.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
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
        SelenideElement value = $(".table-responsive").$(byText(label)).parent();

        if (values.isEmpty()) {
            value.shouldHave(exactText(label));
        } else {
            value.shouldHave(text(values));
        }

        return this;
    }
}
