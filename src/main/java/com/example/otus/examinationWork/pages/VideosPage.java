package com.example.otus.examinationWork.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class VideosPage {
    private static final Logger logger = LogManager.getLogger(VideosPage.class);
    public WebDriver driver;

    public VideosPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * локатор вкладки кнопки More Filters
     */
    @FindBy(xpath = "//div[@href='#collapseMoreFilters']")
    private WebElement buttonMoreFilters;

    /**
     * локатор вкладки кнопки Category
     */
    @FindBy(id = "filter_category")
    private WebElement buttonFilterCategory;

    /**
     * локатор элементов для чек-боксов
     */
    @FindBy(xpath = "//div[@class='evnt-checkbox form-check']")
    private WebElement checkboxFilterVariable;

    /**
     * локатор вкладки кнопки Location
     */
    @FindBy(id = "filter_location")
    private WebElement buttonFilterLocation;

    /**
     * локатор вкладки кнопки Language
     */
    @FindBy(id = "filter_language")
    private WebElement buttonFilterLanguage;

    /**
     * локатор поля для ввода значения в фильтр
     */
    @FindBy(xpath = "//div[contains(@class,'evnt-search-filter')]/input")
    private WebElement searchFilter;

    /**
     * локатор поиска всех результатов
     */
    @FindAll({@FindBy(xpath = "//section[@class='evnt-panel evnt-talks-panel']//div[@class='evnt-talks-row']/div")})
    private List<WebElement> listVideos;

    /**
     * Метод для ввода в поле поиска
     */
    public void inputSearchFilter(String value) {
        searchFilter.sendKeys(value);
        logger.info("В фильтр поиска введено значение: " + value);
    }

    /**
     * Метод возврата введенного значения в поле поиска
     */
    public String getValueSearchFilter() {
        String result = searchFilter.getAttribute("value");
        logger.info("В поле фильтра отображается значение: " + result);
        return result;
    }

    /**
     * Метод нажатия на кнопку More Filters
     */
    public void clickButtonMoreFilters() {
        buttonMoreFilters.click();
        logger.info("Нажата кнопка More Filters");
    }

    /**
     * Метод нажатия на кнопку Category
     */
    public void clickButtonFilterCategory() {
        buttonFilterCategory.click();
        logger.info("Нажата кнопка фильтра Category");
    }

    /**
     * Метод нажатия на кнопку Location
     */
    public void clickButtonFilterLocation() {
        buttonFilterLocation.click();
        logger.info("Нажата кнопка фильтра Location");
    }

    /**
     * Метод нажатия на кнопку Language
     */
    public void clickButtonFilterLanguage() {
        buttonFilterLanguage.click();
        logger.info("Нажата кнопка фильтра Language");
    }

    /**
     * Метод нажатия на чекбокс (опционально)
     */
    public void clickCheckboxFilterVariable(String value) {
        checkboxFilterVariable.findElement(By.xpath(String.format("//label[text()='%s']", value))).click();
        logger.info("Нажат чек-бокс: " + value);
    }

    /**
     * Метод позвращает список всех названий видео
     */
    public ArrayList<String> getListCardName() {
        ArrayList<String> list = new ArrayList<>();
        for (WebElement item : listVideos) {
            list.add(item.findElement(By.xpath("//div[@class='evnt-card-table']//div[@class='evnt-card-body']//h1")).getText());
        }
        return list;
    }
}
