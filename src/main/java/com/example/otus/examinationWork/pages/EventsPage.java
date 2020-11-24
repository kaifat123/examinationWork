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
     * Метод нажатия на кнопку выбора актуальности мероприятий (опционально)
     */
    public void clickEventTabElements(String eventTab) {
        eventTabElements.findElement(By.xpath(String.format("//span[text()=\"%s\"]", eventTab))).click();
        Allure.addAttachment("Выполнено нажатие кнопки ", eventTab);
        logger.info("Выполнено нажатие кнопки: " + eventTab);
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
    public Integer getCountUpcomingEvents() {
        logger.info("Возвращаем счетчик событий");
        return Integer.parseInt(eventTabElements.findElement(By.xpath("//span[text()=\"Upcoming events\"]/following-sibling::span[contains(@class,'evnt-tab-counter')]")).getText());
    }

    /**
     * Метод получения дат событий
     */
    public ArrayList<String> getAllDateEventsWeek(){
        ArrayList<String> listDate = new ArrayList<>();
        for (WebElement element:listEventToThisWeek) {
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
