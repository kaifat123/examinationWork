package com.example.otus.examinationWork.steps;

import com.example.otus.examinationWork.helpers.DriverHooks;
import com.example.otus.examinationWork.pages.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MainPageStepDefs {
    private final WebDriver driver = DriverHooks.getWebDriver();
    private final MainPage loginPage = new MainPage(driver);
    private static final Logger logger = LogManager.getLogger(MainPageStepDefs.class);
}
