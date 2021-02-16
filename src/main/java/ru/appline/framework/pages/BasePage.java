package ru.appline.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.ManagerPages;


import static ru.appline.framework.managers.DriverManager.getDriver;

/**
 * @author Arkadiy_Alaverdyan
 * Базовый класс всех страничек
 */
public class BasePage {

    /**
     * Менеджер страничек
     *
     * @see ManagerPages
     */
    protected ManagerPages app = ManagerPages.getManagerPages();

    /**
     * Объект для имитации реального поведения мыши или клавиатуры
     *
     * @see Actions
     */
    protected Actions action = new Actions(getDriver());

    /**
     * Объект для выполнения любого js кода
     *
     * @see JavascriptExecutor
     */
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    /**
     * Объект явного ожидания
     * При применении будет ожидать задонного состояния 10 секунд с интервалом в 1 секунду
     *
     * @see WebDriverWait
     */
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    /**
     * Конструктор позволяющий инициализировать все странички и их эелементы помеченные анотацией {@link FindBy}
     * Подробнее можно просмотреть в класс {@link org.openqa.selenium.support.PageFactory}
     *
     * @see FindBy
     * @see PageFactory
     * @see PageFactory#initElements(WebDriver, Object)
     */
    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Функция позволяющая скролить до любого элемента с помощью js
     *
     * @param element - веб-элемент странички
     * @see JavascriptExecutor
     */
    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Функция позволяющая производить scroll до любого элемента с помощью js со смещение
     * Смещение задается количеством пикселей по вертикали и горизонтали, т.е. смещение до точки (x, y)
     *
     * @param element - веб-элемент странички
     * @param x       - параметр координаты по горизонтали
     * @param y       - параметр координаты по вертикали
     * @see JavascriptExecutor
     */
    public void scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        js.executeScript(code, element, x, y);
    }

    public void scrollIntoMiddle(WebElement element){
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, element);
    }


    /**
     * Явное ожидание состояния кликабельности элемента
     *
     * @param element - веб-элемент который требует проверки на кликабельность
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement elementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-елемент поле ввода
     * @param value - значение вводимое в поле
     */
    public void fillInputField(WebElement field, String value) {
        //scrollToElementJs(field);
        elementToBeClickable(field).click();
        field.sendKeys(value);
    }

    /**
     * Общий метод по заполнению полей с датой
     *
     * @param field - веб-елемент поле с датой
     * @param value - значение вводимое в поле с датой
     */
    public void fillDateField(WebElement field, String value) {
        scrollToElementJs(field);
        field.sendKeys(value);
    }
}
