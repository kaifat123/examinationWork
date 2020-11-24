package com.example.otus.examinationWork.steps;

import com.example.otus.examinationWork.helpers.DriverHooks;
import com.example.otus.examinationWork.pages.EventsPage;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class EventsPageStepDefs {
    private WebDriver driver = DriverHooks.getWebDriver();
    private EventsPage eventsPage = new EventsPage(driver);
    private static final Logger logger = LogManager.getLogger(EventsPageStepDefs.class);

    @И("Пользователь нажимает кнопку {string}")
    public void clickEventsTabNavigation(String events) {
        eventsPage.clickEventTabElements(events);
    }

    @Тогда("Пользователь проверяет отображение карточек мероприятий")
    public void checkCardsEvents() {
        Assert.assertTrue(eventsPage.getListAllEvents().size() > 0);
    }

    @Тогда("Пользователь сравнивает количество карточек мероприятий и счетчик")
    public void equalsCountEvents() {
        Integer count = eventsPage.getCountUpcomingEvents();
        Integer sizeList = eventsPage.getListAllEvents().size();
        Allure.addAttachment("Счетчик событий: ", count.toString());
        Allure.addAttachment("Колличество карточек событий: ", sizeList.toString());
        Assert.assertSame(count, sizeList);
    }

    @Тогда("Проверить, что в карточке информация о {string} присутствует")
    public void checkInfoCard(String info){
        Assert.assertFalse(eventsPage.getInfoCardEvents(info));
    }
}
