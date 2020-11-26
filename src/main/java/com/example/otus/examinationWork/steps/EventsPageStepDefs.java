package com.example.otus.examinationWork.steps;

import com.example.otus.examinationWork.helpers.DriverHooks;
import com.example.otus.examinationWork.pages.EventCardPage;
import com.example.otus.examinationWork.pages.EventsPage;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class EventsPageStepDefs {
    private WebDriver driver = DriverHooks.getWebDriver();
    private EventsPage eventsPage = new EventsPage(driver);
    private EventCardPage eventCardPage = new EventCardPage(driver);
    private static final Logger logger = LogManager.getLogger(EventsPageStepDefs.class);

    @И("Пользователь нажимает кнопку {string}")
    public void clickEventsTabNavigation(String events) {
        eventsPage.clickEventTabElements(events);
    }

    @Затем("Пользователь нажимает на фильтр локации, вводит в поиск {string} и производит выбор локации")
    public void actionFilterLocationCanada(String input) {
        DriverHooks.wait
                .until(invisibilityOfElementLocated(By.xpath("//div[@id='app']//section[@class='evnt-panel evnt-talks-panel']//div[@class='evnt-global-loader']")));
        eventsPage.clickFilterLocation();
        eventsPage.setInputLocationFilter(input);
        eventsPage.clickCheckBoxCanada();
        saveAllureScreenshot();
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

    @Затем("Пользователь нажимает на любую карточку")
    public void clickAnyEventCard() {
        String nameEvent = eventsPage.clickFirstEventInList();
        Assert.assertEquals(nameEvent, eventCardPage.getNameEventInCard());
    }

    @Тогда("Проверить, что дата проведения мероприятий больше или равна текущей даты")
    public void checkDatePath1() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Calendar dateNow = Calendar.getInstance();
        Calendar dateEvent = Calendar.getInstance();
        dateNow.setTime(new Date());
        logger.info("Текущая дата: " + dateNow.getTime());
        for (String item : eventsPage.getAllDateEventsWeek()) {
            try {
                Date data = formatter.parse(item);
                dateEvent.setTime(data);
                dateEvent.set(Calendar.HOUR, 23);
                dateEvent.set(Calendar.MINUTE, 59);
                logger.info("Дата события: " + dateEvent.getTime());
                Assert.assertTrue(dateEvent.getTime().getTime() >= dateNow.getTime().getTime());
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

    @Затем("Проверить странице с информацией о мероприятии отображение блоков$")
    public void checkInformationAboutEvent(List<String> list) {
        eventCardPage.checkInfoInCard(list);
        saveAllureScreenshot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
