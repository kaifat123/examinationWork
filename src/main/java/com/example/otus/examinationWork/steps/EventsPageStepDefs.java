package com.example.otus.examinationWork.steps;

import com.example.otus.examinationWork.helpers.DriverHooks;
import com.example.otus.examinationWork.pages.EventsPage;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EventsPageStepDefs {
    private WebDriver driver = DriverHooks.getWebDriver();
    private EventsPage eventsPage = new EventsPage(driver);
    private static final Logger logger = LogManager.getLogger(EventsPageStepDefs.class);

    @И("Пользователь нажимает кнопку {string}")
    public void clickEventsTabNavigation(String events) {
        eventsPage.clickEventTabElements(events);
    }

    @Затем("Пользователь нажимает на фильтр локации, вводит в поиск {string} и производит выбор локации")
    public void actionFilterLocationCanada(String input){
        eventsPage.clickFilterLocation();
        eventsPage.setInputLocationFilter(input);
        eventsPage.clickCheckBoxCanada();
        eventsPage.clickFilterLocation();
    }

    @Тогда("Пользователь проверяет отображение карточек мероприятий")
    public void checkCardsEvents() {
        Assert.assertTrue(eventsPage.getListAllEvents().size() > 0);
    }

    @Тогда("Пользователь сравнивает количество карточек мероприятий и счетчик {string}")
    public void equalsCountEvents(String event) {
        Integer count = eventsPage.getCountUpcomingEvents(event);
        Integer sizeList = eventsPage.getListAllEvents().size();
        Allure.addAttachment("Счетчик событий: ", count.toString());
        Allure.addAttachment("Колличество карточек событий: ", sizeList.toString());
        Assert.assertSame(count, sizeList);
    }

    @Тогда("Проверить, что в карточке информация о {string} присутствует")
    public void checkInfoCard(String info) {
        Assert.assertFalse(eventsPage.getInfoCardEvents(info));
    }

    @Тогда("Проверить, что дата проведения мероприятий больше или равна текущей даты")
    public void checkDatePath1() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date dateNow = new Date();
        logger.info("Текущая дата: " + dateNow);
        for (String item : eventsPage.getAllDateEventsWeek()) {
            try {
                Date dateEvent = formatter.parse(item);
                logger.info("Дата события: " + dateEvent);
                Assert.assertTrue(dateEvent.getTime() >= dateNow.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Тогда("Проверить, что дата проведения мероприятий находятся в пределах текущей недели")
    public void checkDatePath2() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Calendar dateEndWeek = Calendar.getInstance();
        dateEndWeek.setTime(new Date());
        logger.info("Текущая дата: " + dateEndWeek.getTime());
        dateEndWeek.set(Calendar.DAY_OF_WEEK, 7);
        logger.info("Дата конца недели: " + dateEndWeek.getTime());

        for (String item : eventsPage.getAllDateEventsWeek()) {
            try {
                Date dateEvent = formatter.parse(item);
                logger.info("Дата события: " + dateEvent);
                Assert.assertTrue(dateEndWeek.getTime().getTime() >= dateEvent.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
