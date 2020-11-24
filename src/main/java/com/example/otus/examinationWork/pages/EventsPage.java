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

import java.util.List;

public class EventsPage {
    private static final Logger logger = LogManager.getLogger(EventsPage.class);
    public WebDriver driver;

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
     * Метод нажатия на кнопку выбора актуальности мероприятий (опционально)
     */
    public void clickEventTabElements(String eventTab) {
        eventTabElements.findElement(By.xpath(String.format("//span[text()=\"%s\"]", eventTab))).click();
        Allure.addAttachment("Выполнено нажатие кнопки ", eventTab);
        logger.info("Выполнено нажатие кнопки: " + eventTab);
    }

    /**
     * Метод метод получения массива событий
     */
    public List<WebElement> getListAllEvents() {
        logger.info("Возвращаем список событий");
        return listAllEvents;
    }

    /**
     * Метод метод получения счетчика событий
     */
    public Integer getCountUpcomingEvents() {
        logger.info("Возвращаем счетчик событий");
        return Integer.parseInt(eventTabElements.findElement(By.xpath("//span[text()=\"Upcoming events\"]/following-sibling::span[contains(@class,'evnt-tab-counter')]")).getText());
    }
}
