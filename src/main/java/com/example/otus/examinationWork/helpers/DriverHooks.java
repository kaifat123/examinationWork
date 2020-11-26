package com.example.otus.examinationWork.helpers;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverHooks {
    private static WebDriver driver;
    public static WebDriverWait wait;
    public static Logger logger = LogManager.getLogger(DriverHooks.class);

    @Before
    public static void setUp() throws MalformedURLException {

        String docker = System.getProperty("docker");
        String browser = System.getProperty("browser");
        String options = System.getProperty("options");
        if (docker != null && docker.equals("true")) {
            String selenoidURL = "http://192.168.0.102:4444/wd/hub";
            DesiredCapabilities caps = new DesiredCapabilities();
            if (browser != null) {
                caps.setBrowserName(browser);
            } else {
                caps.setBrowserName(ConfProperties.getProperty("driver.browser.name"));
            }
            caps.setVersion("");
            caps.setCapability("enableVNC", true);
            caps.setCapability("screenResolution", "2048x1280");
            driver = new RemoteWebDriver(new URL(selenoidURL), caps);

        } else {
            if (browser == null) {
                driver = WebDriverHelper.getDriver();
            } else if (options != null) {
                driver = WebDriverHelper.getDriverWithOptions(browser, options);
            } else {
                driver = WebDriverHelper.getDriver(browser);
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 2);


    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    @After
    public static void setDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.quit();
                logger.info("Закрытие браузера");
            }
        }
        driver.quit();
        logger.info("Закрытие браузера");
    }
}
