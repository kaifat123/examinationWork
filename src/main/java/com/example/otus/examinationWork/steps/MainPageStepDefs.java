package com.example.otus.examinationWork.steps;

import com.example.otus.examinationWork.helpers.DriverHooks;
import com.example.otus.examinationWork.pages.MainPage;
import io.cucumber.java.ru.Когда;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MainPageStepDefs {
    private  WebDriver driver = DriverHooks.getWebDriver();
    private  MainPage mainPage = new MainPage(driver);
    private static final Logger logger = LogManager.getLogger(MainPageStepDefs.class);

    @Когда("Пользователь открывает главную страницу")
    public void openPage(){
        mainPage.openPage();
    }
}
