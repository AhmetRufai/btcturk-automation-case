package com.base;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Locale;

import static com.base.BrowserNamesEnum.*;

public class BaseWebClient {
    protected WebDriverWait wait;
    protected Scenario scenario;

    static {
        //The language of Java is set in English
        Locale.setDefault(new Locale("en", "EN"));
    }

    public void getScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public WebDriver setupDriver(String browser) {
        WebDriver driver = null;
        //      The browser driver depending on the parameter is downloaded automatically.
        if (browser.equalsIgnoreCase(CHROME.name())) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase(FIREFOX.name())) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase(EDGE.name())) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase(OPERA.name())) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if (browser.equalsIgnoreCase(SAFARI.name())) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        } else {
            Assert.fail("The browser name in the parameter is not one of the known browsers. " +
                    "Please try with a different browser");
        }
        scenario.log(String.format("%s browser driver'ı başarıyla yüklendi", browser));

        return driver;
    }

    public void gotoPage(WebDriver driver, String url, int timeOutInSeconds, int sleepInMiles, By byForCheckElement) {
        driver.get(url);
        driver.manage().window().maximize();
//      Checking whether the page is loaded
        checkPage(driver, timeOutInSeconds, sleepInMiles, byForCheckElement);

    }

    public void checkPage(WebDriver driver, int timeOutInSeconds, int sleepInMiles, By byForCheckElement) {
        wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMiles);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byForCheckElement));
    }

    public void checkPageWithText(WebDriver driver, By byForCheckElement, String text) {
        boolean isSuccess = driver.findElement(byForCheckElement).getText().equals(text);
        Assert.assertTrue(isSuccess, String.format("%s text'i bekleniyordu fakat bulunamadı", text));
    }

    public void checkClickable(WebDriver driver, int timeOutInSeconds, int sleepInMiles, By byForCheckElement) {
        wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMiles);
        wait.until(ExpectedConditions.elementToBeClickable(byForCheckElement));
    }

    public void tearDown(WebDriver driver) {
        driver.quit();
    }

    public void getSsOnFailure(WebDriver driver) {
        if (scenario.isFailed()) {
            byte[] data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", "Fail Screenshot");
        }
    }

    public String getElementText(WebDriver driver, int timeOutInSeconds, int sleepInMiles, By by) {
        checkPage(driver, timeOutInSeconds, sleepInMiles, by);
        return driver.findElement(by).getText();
    }

    public void clickElement(WebDriver driver, int timeOutInSeconds, int sleepInMiles, By by) {
        checkPage(driver, timeOutInSeconds, sleepInMiles, by);
        checkClickable(driver, timeOutInSeconds, sleepInMiles, by);
        driver.findElement(by).click();
    }

    public void sendTextToElement(WebDriver driver, int timeOutInSeconds, int sleepInMiles, By by, String text) {
        checkPage(driver, timeOutInSeconds, sleepInMiles, by);
        driver.findElement(by).sendKeys(text);
    }
}
