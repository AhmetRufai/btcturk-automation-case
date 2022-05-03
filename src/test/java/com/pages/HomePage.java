package com.pages;

import com.base.BaseWebClient;
import com.base.config.AppSettings;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.pages.SimpleBuySellPage.*;

public class HomePage extends BaseWebClient {

    public static final By HREF_HOME_PAGE = By.xpath("//a[@href='https://sso.btcturk.com/register']");
    public static final By HREF_SIMPLE_BUY_SELL_PAGE = By.xpath("//a[@href='/basit/al-sat']");

    protected static final int TIME_OUT_IN_SECONDS_FOR_MAIN_PAGE = 5;
    protected static final int SLEEP_IN_MILLIS_FOR_MAIN_PAGE = 100;
    private static final String URL = AppSettings.Instance.uiHomePage.url;

    public void goToHomePage(WebDriver driver, Scenario scenario) {
        gotoPage(driver, URL, TIME_OUT_IN_SECONDS_FOR_MAIN_PAGE, SLEEP_IN_MILLIS_FOR_MAIN_PAGE, HREF_HOME_PAGE);
        scenario.log("Ana Sayfa başarılı bir şekilde yüklendi");
    }

    public void clickSimpleBuySellTab(WebDriver driver, Scenario scenario) {
        driver.findElement(HREF_SIMPLE_BUY_SELL_PAGE).click();
        checkPage(driver, TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS, LI_SIMPLE_BUY_SELL_ACTIVE_TAB);
        scenario.log("Basit Al-Sat sayfası başarılı bir şekilde yüklendi");
    }
}
