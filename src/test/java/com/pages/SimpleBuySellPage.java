package com.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class SimpleBuySellPage extends HomePage {

    protected static final int TIME_OUT_IN_SECONDS = 10;
    protected static final int SLEEP_IN_MILLIS = 100;

    protected static final By LI_SIMPLE_BUY_SELL_ACTIVE_TAB = By.xpath("//li[@routerlinkactive='active' and @class='active']//a[@href='/basit/al-sat']");
    public static final By ID_24H_MIN_PRICE = By.id("ex-advanced-ticker-low");
    public static final By ID_24H_MAX_PRICE = By.id("ex-advanced-ticker-high");

    private static final By BTN_SWITCH_CHANGE = By.xpath("//button[@class='pair-button ng-star-inserted']");
    private static final By INPUT_SEARCH_CHANGE = By.xpath("//input[@placeholder='Arama']");
    private static final By LABEL_SELECT_TRY = By.xpath("//label[normalize-space()='TRY']");
    private static final By DIV_SELECT_USDT_TRY = By.xpath("//div[@class='ps']//div[@class='ps-content']");
    private static final By BTN_USDT_TRY_SELECTED_FOR_PAGE_CHECK = By.xpath("//button[@type='button']//span[@class='pair']");
    private static final String INPUT_TEXT_FOR_SEARCH = "USDT";
    private static final String TEXT_FOR_CHECK_ELEMENT_TEXT = "USDT/TRY";

    public double getPrice(WebDriver driver, By by) {
        String elementText = getElementText(driver, TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS, by);
        return Double.parseDouble(elementText.replace(",", "."));
    }

    public void compareMaxAndMinPrice(double maxPrice, double minPrice) {
        boolean isSuccess = maxPrice >= minPrice;
        Assert.assertTrue(isSuccess, String.format("""
                '24s En Yüksek' değeri, '24s En Düşük' değerlerinden küçük;\s
                 24s En Yüksek: %.3f\s
                 24s En Düşük: %.3f\s""", maxPrice, minPrice));
    }

    public void selectUsdtTry(WebDriver driver) {
        clickElement(driver, TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS, BTN_SWITCH_CHANGE);
        sendTextToElement(driver, TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS, INPUT_SEARCH_CHANGE, INPUT_TEXT_FOR_SEARCH);
        clickElement(driver, TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS, LABEL_SELECT_TRY);
        clickElement(driver, TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS, DIV_SELECT_USDT_TRY);
        checkPage(driver, TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS, BTN_USDT_TRY_SELECTED_FOR_PAGE_CHECK);
        checkPageWithText(driver, BTN_USDT_TRY_SELECTED_FOR_PAGE_CHECK, TEXT_FOR_CHECK_ELEMENT_TEXT);
    }
}
