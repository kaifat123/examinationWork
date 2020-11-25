package com.example.otus.examinationWork.steps;

import com.example.otus.examinationWork.helpers.DriverHooks;
import com.example.otus.examinationWork.pages.MainPage;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Когда;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class MainPageStepDefs {
    private WebDriver driver = DriverHooks.getWebDriver();
    private MainPage mainPage = new MainPage(driver);
    private static final Logger logger = LogManager.getLogger(MainPageStepDefs.class);

    @Когда("Пользователь открывает главную страницу")
    public void openPage() {
        mainPage.openPage();
        Allure.addAttachment("Заголовок страницы", driver.getTitle());
        Assert.assertEquals("Events Portal", driver.getTitle());
    }

    @Затем("Пользователь переходит по вкладке {string}")
    public void clickTabVariable(String tab) {
        mainPage.clickTabsVariable(tab);
    }
}
