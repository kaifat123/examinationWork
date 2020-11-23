package com.example.otus.examinationWork.helpers;

import com.example.otus.examinationWork.factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WebDriverHelper {
    private static Logger logger = LogManager.getLogger(WebDriverHelper.class);
    private static WebDriver driver;

    public static WebDriver getDriver() {
        String browser = ConfProperties.getProperty("driver.browser.name");
        browser = browser.toLowerCase();
        driver = WebDriverFactory.create(browser);
        logger.info("Драйвер " + browser + " поднят");
        return driver;
    }

    public static WebDriver getDriver(String browser) {
        driver = WebDriverFactory.create(browser.toLowerCase());
        logger.info("Драйвер " + browser + " поднят");
        return driver;
    }

    public static WebDriver getDriverWithOptions(String browser, String options) {
        driver = WebDriverFactory.create(browser.toLowerCase(), options);
        logger.info("Драйвер " + browser + " поднят");
        logger.info("Будут применяться следующие настройки драйвера: " + options);
        return driver;
    }
}
