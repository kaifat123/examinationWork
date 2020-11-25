package com.example.otus.examinationWork.steps;

import com.example.otus.examinationWork.helpers.DriverHooks;
import com.example.otus.examinationWork.pages.VideosPage;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class VideoPageStepDefs {
    private WebDriver driver = DriverHooks.getWebDriver();
    private VideosPage videosPage = new VideosPage(driver);
    private static final Logger logger = LogManager.getLogger(VideoPageStepDefs.class);

    @Затем("Пользователь нажимает на  кнопку \"More Filters\"")
    public void clickButtonMoreFilters() {
        videosPage.clickButtonMoreFilters();
    }

    @Тогда("Пользователь проверяет наличие найденых записей")
    public void checkPresentVideos() {
        Assert.assertTrue(videosPage.getListCardName().size() > 0);
        saveAllureScreenshot();
    }

    @И("Пользователь вводит ключевое слово {string} в поле поиска")
    public void setFilterField(String value) {
        videosPage.inputSearchFilter(value);
    }

    @Тогда("На странице отображаются доклады, содержащие в названии ключевое слово поиска")
    public void checkNameReportsValueFilter() {
        String value = videosPage.getValueSearchFilter();
        DriverHooks.wait
                .until(invisibilityOfElementLocated(By.xpath("//section[@class='evnt-panel evnt-talks-panel']//div[@class='evnt-loader']")));
        for (String item : videosPage.getListCardName()) {
            Assert.assertTrue(item.contains(value));
        }
    }

    @Затем("Пользователь устанавливает следующие фильтры$")
    public void setAllFiltersOnVideoPage(Map<String, String> dataTable) {
        dataTable.forEach(this::setFilters);
    }

    public void setFilters(String pathMethod, String checkBoxName) {
        Optional<Method> method = Arrays.stream(VideosPage.class.getMethods()).filter(m -> m.getName().contains(pathMethod)).findFirst();
        try {
            method.get().invoke(videosPage);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        videosPage.clickCheckboxFilterVariable(checkBoxName);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
