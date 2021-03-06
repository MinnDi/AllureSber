package ru.appline.framework.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.appline.framework.basetestsclass.BaseTests;

public class HWTest extends BaseTests {
    @Test()
    @Tag("SmokeTest")
    @DisplayName("Оформление ипотеки на готовое жильё")
    @Description("Демонстрационный e2e сценарий")
    public void startTest() {
        app.getStartPage()
                .selectMenuButton("Ипотека")
                .selectMortgageType("Ипотека на готовое жильё")
                .fillField("Стоимость недвижимости", "5180000")
                .fillField("Первоначальный взнос", "3058000")
                .fillField("Срок кредита", "30")
                .checkoutSwitch("Страхование жизни и здоровья", false)
                .checkoutSwitch("Молодая семья", false)
                .checkResults("Сумма кредита", "2122000")
                .checkResults("Ежемесячный платеж", "16618")
                .checkResults("Необходимый доход", "21393")
                .checkResults("Процентная ставка","11");
    }
}
