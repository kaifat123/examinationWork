package com.example.otus.examinationWork.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class WebDriverFactory {
    public static WebDriver create(String webDriverName) {
        return Factory.create(webDriverName);
    }

    public static WebDriver create(String webDriverName, String options) {
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(options.split(";")));
        return Factory.create(webDriverName.toLowerCase(), listOptions);
    }
}

interface Driver {
    WebDriver create();

    WebDriver create(ArrayList<String> options);
}

class DriverChrome implements Driver {

    public WebDriver create() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @Override
    public WebDriver create(ArrayList<String> listOptions) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(listOptions);
        return new ChromeDriver(options);
    }
}

class DriverFox implements Driver {

    public WebDriver create() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    @Override
    public WebDriver create(ArrayList<String> listOptions) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(listOptions);
        return new FirefoxDriver(options);
    }
}

class DriverIE implements Driver {

    public WebDriver create() {

        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }

    @Override
    public WebDriver create(ArrayList<String> listOptions) {
        WebDriverManager.iedriver().setup();
        System.out.println("У браузера IE отсутствуют опции запуска");
        return new InternetExplorerDriver();
    }
}

class DriverEDGE implements Driver {

    public WebDriver create() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    @Override
    public WebDriver create(ArrayList<String> listOptions) {
        WebDriverManager.edgedriver().setup();
        System.out.println("У браузера EDGE отсутствуют опции запуска");
        return new EdgeDriver();
    }
}


class Factory {
    private static Logger logger = LogManager.getLogger(Factory.class);

    public static WebDriver create(String browser) {
        switch (browser) {
            case ("chrome"):
                return new DriverChrome().create();
            case ("firefox"):
                return new DriverFox().create();
            case ("ie"):
                return new DriverIE().create();
            case ("edge"):
                return new DriverEDGE().create();
            default:
                logger.error("Браузер указан не верно");
                System.exit(0);
                return null;
        }
    }

    public static WebDriver create(String browser, ArrayList<String> options) {
        switch (browser) {
            case ("chrome"):
                return new DriverChrome().create(options);
            case ("firefox"):
                return new DriverFox().create(options);
            case ("ie"):
                return new DriverIE().create(options);
            case ("edge"):
                return new DriverEDGE().create(options);
            default:
                logger.error("Браузер указан не верно");
                System.exit(0);
                return null;
        }
    }
}

