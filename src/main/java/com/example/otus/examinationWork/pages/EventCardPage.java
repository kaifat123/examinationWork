package com.example.otus.examinationWork.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EventCardPage {
    private static final Logger logger = LogManager.getLogger(EventCardPage.class);
    public WebDriver driver;

    public EventCardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Локатор названия события
     */
    @FindBy(xpath = "//section[@id='home']/div[@class='evnt-panel-wrapper']/div[@class='evnt-text-wrapper']//h1")
    private WebElement nameEventInCard;

    /**
     * Локатор кнопки регистрация
     */
    @FindBy(xpath = "//button[.='Register']")
    private WebElement buttonRegister;

    /**
     * Локатор информации о дате и времени
     */
    @FindBy(xpath = "//div[@class='evnt-icon-points-container evnt-icon-points-slider']/div[1]//div[@class='evnt-icon-info']/h4")
    private WebElement infoDateAndTime;

    /**
     * Локатор списка мероприятий
     */
    @FindAll({@FindBy(xpath = "//div[@id='agenda']/section//div[@class='evnt-agenda-wrapper']/div")})
    private List<WebElement> listScheduleProgramEvent;

    /**
     * Метод возвращает спискок программы мероприятия
     */
    public List<WebElement> getListScheduleProgramEvent() {
        return listScheduleProgramEvent;
    }

    /**
     * Возврат веб элемент дата и время для дальнейшей манипуляции
     */
    public WebElement getInfoDateAndTime() {
        logger.info("Возвращаем веб элемент о дате и времени");
        return infoDateAndTime;
    }

    /**
     * Возврат веб элемента кнопки регистрация для дальнейшей манипуляции
     */
    public WebElement getButtonRegister() {
        logger.info("Возвращаем веб элемент кнопки регистрации");
        return buttonRegister;
    }

    /**
     * Метод получения названия события
     */
    public String getNameEventInCard() {
        logger.info("Возвращаем название мероприятия из карточки");
        return nameEventInCard.getText();
    }

    public void checkInfoInCard(List<String> list) {
        boolean result;
        for (String item :
                list) {
            result = false;
            switch (item) {
                case "кнопка для регистрации": {
                    Assert.assertTrue(getButtonRegister().isDisplayed());
                    logger.info(String.format("Элемент %s присутствует на странице",item));
                    break;
                }
                case "дата и время":{
                    Assert.assertTrue(getInfoDateAndTime().isDisplayed());
                    logger.info(String.format("Элемент %s присутствует на странице",item));
                    break;
                }
                case "программа мероприятия":{
                    Assert.assertTrue(getListScheduleProgramEvent().size()>0);
                    logger.info(String.format("Элементов \"%s\" присутствует на странице больше нуля",item));
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + item);
            }
        }
    }
}
