package com.example.otus.examinationWork.pages;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class EventsPage {
    private static final Logger logger = LogManager.getLogger(EventsPage.class);
    public WebDriver driver;
    private final String conferenceVenue = "//div[@class='evnt-card-heading']/div/div/p[@class='online']/span";
    private final String language = "//div[@class='evnt-card-heading']/div/div/p[@class='language']/span";
    private final String eventName = "//div[@class='evnt-card-body']/div/div[@class='evnt-event-name']/h1/span";
    private final String eventDate = "//div[@class='evnt-card-body']/div/div[@class='evnt-event-dates']/div/div/p/span[1]";
    private final String registrationInformation = "//div[@class='evnt-card-body']/div/div[@class='evnt-event-dates']/div/div/p/span[2]";
    private final String listOfSpeakers = "//div[@class='evnt-card-footer']/div/div/div/div";

    public EventsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * локатор кнопки выбора актуальности мероприятий
     */
    @FindBy(xpath = "//li[@class='evnt-tab-item nav-item']")
    private WebElement eventTabElements;

    /**
     * локатор всех карточек мероприятий
     */
    @FindAll({@FindBy(xpath = "//div[@class='evnt-events-tabs-container tab-content']//div[@class='evnt-events-row']/div")})
    private List<WebElement> listAllEvents;

    /**
     * локатор всех карточек мероприятий текущей недели
     */
    @FindAll({@FindBy(xpath = "//div[@class='evnt-cards-container']/h3[text()=\"This week\"]/following-sibling::div/div")})
    private List<WebElement> listEventToThisWeek;

    /**
     * локатор фильтра местопроведения мероприятия
     */
    @FindBy(id = "filter_location")
    private WebElement filterLocation;

    /**
     * локатор ввода текста в фильтр
     */
    @FindBy(xpath = "//div[@id='filter_location']/following-sibling::div//input[@placeholder='Start typing']")
    private WebElement inputLocationFilter;

    /**
     * локатор чек-бокса Канада
     */
    @FindBy(xpath = "//div[@id='filter_location']/following-sibling::div//div[@class='evnt-checkbox form-check']//span[text()='Canada']")
    private WebElement checkBoxCanada;

    /**
     * Метод нажатия на кнопку выбора актуальности мероприятий (опционально)
     */
    public void clickEventTabElements(String eventTab) {
        eventTabElements.findElement(By.xpath(String.format("//span[text()=\"%s\"]", eventTab))).click();
        Allure.addAttachment("Выполнено нажатие кнопки ", eventTab);
        logger.info("Выполнено нажатие кнопки: " + eventTab);
    }

    public void clickCheckBoxCanada(){
        checkBoxCanada.click();
        logger.info("Выбрали локацию в фильтре");
    }

    /**
     * Метод ввода текста в фильтр локации
     */
    public void setInputLocationFilter(String value) {
        inputLocationFilter.sendKeys(value);
        logger.info("Ввели текст поиска " + value);
        Allure.addAttachment("Ввели текст поиска",value);
    }

    /**
     * Метод нажатия на кнопку фильтра локации
     */
    public void clickFilterLocation() {
        filterLocation.click();
        logger.info("Нажали на кнопку фильтра локации");
    }

    /**
     * Метод получения массива событий
     */
    public List<WebElement> getListAllEvents() {
        logger.info("Возвращаем список событий");
        return listAllEvents;
    }

    /**
     * Метод получения счетчика событий
     */
    public Integer getCountUpcomingEvents(String event) {
        logger.info("Возвращаем счетчик событий");
        return Integer.parseInt(eventTabElements.findElement(By.xpath(String.format("//span[text()=\"%s\"]/following-sibling::span[contains(@class,'evnt-tab-counter')]",event))).getText());
    }

    /**
     * Метод получения дат событий
     */
    public ArrayList<String> getAllDateEventsWeek() {
        ArrayList<String> listDate = new ArrayList<>();
        for (WebElement element : listEventToThisWeek) {
            listDate.add(element.findElement(By.xpath(eventDate)).getText());
        }
        return listDate;
    }

    /**
     * Метод получения информации из карточки
     */
    public boolean getInfoCardEvents(String info) {
        boolean result = true;
        String temp;
        WebElement element = getListAllEvents().get(0);
        switch (info) {
            case "место проведения": {
                temp = element.findElement(By.xpath(conferenceVenue)).getText();
                Allure.addAttachment(info, temp);
                result = temp.isEmpty();
                break;
            }
            case "язык": {
                temp = element.findElement(By.xpath(language)).getText();
                Allure.addAttachment(info, temp);
                result = temp.isEmpty();
                break;
            }
            case "название мероприятия": {
                temp = element.findElement(By.xpath(eventName)).getText();
                Allure.addAttachment(info, temp);
                result = temp.isEmpty();
                break;
            }
            case "дата мероприятия": {
                temp = element.findElement(By.xpath(eventDate)).getText();
                Allure.addAttachment(info, temp);
                result = temp.isEmpty();
                break;
            }
            case "информация о регистрации": {
                temp = element.findElement(By.xpath(registrationInformation)).getText();
                Allure.addAttachment(info, temp);
                result = temp.isEmpty();
                break;
            }
            case "список спикеров": {
                int size = element.findElements(By.xpath(listOfSpeakers)).size();
                Allure.addAttachment(info, String.valueOf(size));
                result = size == 0;
                break;
            }
        }
        logger.info(String.format("Возвращаем результат проверки поля \"%s\"", info));
        return result;
    }

}
