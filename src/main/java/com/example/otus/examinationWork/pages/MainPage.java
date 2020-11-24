package com.example.otus.examinationWork.pages;

import com.example.otus.examinationWork.helpers.ConfProperties;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {
    private static final Logger logger = LogManager.getLogger(MainPage.class);
    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * локатор вкладки главной страницы
     */
    @FindBy(xpath = "//div[@class='evnt-platform-header']//ul[@class='evnt-navigation navbar-nav']")
    private WebElement tabsVariable;

    /**
     * Метод нажатия на вкладку (опционально)
     */
    public void clickTabsVariable(String tab) {
        tabsVariable.findElement(By.xpath(String.format("//a[text()=\"%s\"]", tab))).click();
        Allure.addAttachment("Выполнен переход по вкладке ", tab);
        logger.info("Выполнен переход по вкладке: " + tab);
    }

    public void openPage() {
        String url = ConfProperties.getProperty("URL");
        driver.get(url);
        Allure.addAttachment("Открыта URL страница", url);
        logger.info("Открыта страница: " + url);
    }
}
