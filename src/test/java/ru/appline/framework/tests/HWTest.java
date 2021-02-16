package ru.appline.framework.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.appline.framework.basetestsclass.BaseTests;

public class HWTest extends BaseTests {
    @Test()
    @DisplayName("Оформление программы страхования для путешественников")
    @Description("Демонстрационный e2e сценарий")
    public void startTest() {
        app.getStartPage()
                .selectMenuButton("Ипотека")
                .selectMortgageForFinishedBuildings()
                .fillField("Стоимость недвижимости", "5180000")
                .fillField("Первоначальный взнос", "3058000")
                .fillField("Срок кредита", "30")
                .checkoutSwitch("Страхование жизни и здоровья")
                .checkoutSwitch("Молодая семья")
                .checkResults("Сумма кредита", "2122000")
                .checkResults("Ежемесячный платеж", "16922")
                .checkResults("Необходимый доход", "21 784")
                .checkResults("Процентная ставка","11");
    }
}
