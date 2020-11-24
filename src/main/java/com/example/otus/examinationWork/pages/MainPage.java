package com.example.otus.examinationWork.pages;

import com.example.otus.examinationWork.helpers.ConfProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class MainPage {
    private static final Logger logger = LogManager.getLogger(MainPage.class);
    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openPage(){
        String url = ConfProperties.getProperty("URL");
        driver.get(url);
        logger.info("Открыта страница: "+url);
    }
}
