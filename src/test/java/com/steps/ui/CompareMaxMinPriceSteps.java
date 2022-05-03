package com.steps.ui;

import com.base.BaseWebClient;
import com.pages.HomePage;
import com.pages.SimpleBuySellPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static com.pages.SimpleBuySellPage.ID_24H_MAX_PRICE;
import static com.pages.SimpleBuySellPage.ID_24H_MIN_PRICE;

@Slf4j
public class CompareMaxMinPriceSteps {
    private final BaseWebClient baseWebClient = new BaseWebClient();
    private final SimpleBuySellPage simpleBuySellPage = new SimpleBuySellPage();

    private WebDriver driver;
    private Scenario scenario;

    private double maxPriceIn24h;
    private double minPriceIn24h;
    private String browserName;

    @Before
    public void beforeScenario(Scenario scenario) {
        baseWebClient.getScenario(scenario);
        this.scenario = scenario;
    }

    @Given("Open browser with {string} and go to home page")
    public void openBrowserAndGoToHomePage(String browser) {
        browserName = browser;
        driver = baseWebClient.setupDriver(browser);
        HomePage homePage = new HomePage();
        homePage.goToHomePage(driver, scenario);
    }

    @When("Switch to Simple Buy & Sell page")
    public void switchToSimpleBuySellPage() {
        simpleBuySellPage.clickSimpleBuySellTab(driver, scenario);
    }

    @And("Get 24hMax and 24hMin price")
    public void getHMaxAndHMinPrice() {
        maxPriceIn24h = simpleBuySellPage.getPrice(driver, ID_24H_MAX_PRICE);
        minPriceIn24h = simpleBuySellPage.getPrice(driver, ID_24H_MIN_PRICE);
        scenario.log(String.format("""
                '24s En Yüksek' ve '24s En Düşük' değerleri başarıyla çekildi\s
                 24s En Yüksek: %.3f\s
                 24s En Düşük: %.3f""", maxPriceIn24h, minPriceIn24h));
    }

    @Then("Compare max and min price")
    public void compareMaxAndMinPrice() {
        simpleBuySellPage.compareMaxAndMinPrice(maxPriceIn24h, minPriceIn24h);
        scenario.log("'24s En Yüksek' değerinin, '24s En Düşük' değerlerinden büyük olduğu kontrol edildi.");
    }

    @When("Select usdt-try")
    public void selectUsdtTry() {
        simpleBuySellPage.selectUsdtTry(driver);
        scenario.log("USDT/TRY başarılı bir şekilde seçildi");
    }

    @AfterStep
    public void afterStep() {
        baseWebClient.getSsOnFailure(driver);
    }

    @After
    public void tearDown() {
        /* Since this method is called inside the @After hook, it also works for api tests in parallel test runs.
         Since there is no driver in API tests, null check has been added. */
        if (driver != null) {
            baseWebClient.tearDown(driver);
            scenario.log(String.format("%s browser driver'ı başarılı bir şekilde kapatıldı", browserName));
        }
    }
}
