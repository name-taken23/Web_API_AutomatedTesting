package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.cucumber.pages.CartPage;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.ProductPage;
import web.cucumber.pages.ProductsPage;
import web.cucumber.util.AutomationWebsiteUtil;

import java.time.Duration;
import java.util.function.BooleanSupplier;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WriteReviewStepdefs {

    private static WebDriver webDriver;
    private HomePage homePage;
    private CartPage cartPage;
    private ProductsPage productsPage;

    private ProductPage productPage;

    private static final String DRIVER_LOCATION ="src/test/resources/chromedriver";

    @Before("@writereview")
    public void setup(){
        AutomationWebsiteUtil.setDriverLocation(DRIVER_LOCATION);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @After("@writereview")
    public void close(){
        webDriver.close();
        webDriver.quit();
    }

    @Given("I am on a product page")
    public void iAmOnAProductPage() {
        homePage = new HomePage(webDriver);
        productsPage = homePage.goToProductsPage();
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 600)");
        productPage = productsPage.goToProductPage();
    }
    @When("I enter my name and email and review")
    public void iEnterMyNameEmailReview() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 700)");
        productPage.enterName("Jeff Winger");
        productPage.enterEmail("JWinger@Greendale.com");
        productPage.enterReview("I used to be a lawyer!");
    }

    @And("I submit")
    public void iSubmit() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 800)");
        productPage.clickSubmit();
    }

    @Then("I should see a message telling me review was submited")
    public void iShouldSeeAMessageTellingMeReviewWasSubmited() {
        String message = productPage.successMessage();
        Assertions.assertEquals("Thank you for your review.", message);
    }

    @When("I don't enter an email")
    public void iDonTEnterAnEmail() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 700)");
        productPage.enterName("Jeff Winger");
        productPage.enterReview("I used to be a lawyer!");
    }

    @Then("I should get a message telling the email field is missing.")
    public void iShouldGetAMessageTellingTheEmailFieldIsMissing() {
        // Assertions.assertEquals("Please fill in this field.", productPage.invalidInputMessage());
    Assertions.assertTrue(productPage.invalidInputMessage());
    }

    @When("I don't enter an name")
    public void iDonTEnterAnName() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 700)");
        productPage.enterEmail("JWinger@Greendale.com");
        productPage.enterReview("I used to be a lawyer!");
    }

    @Then("I should get a message telling the name field is missing.")
    public void iShouldGetAMessageTellingTheNameFieldIsMissing() {
        Assertions.assertTrue(productPage.invalidInputMessage());
    }

    @When("I don't enter an review")
    public void iDonTEnterAnReview() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 700)");
        productPage.enterName("Jeff Winger");
        productPage.enterEmail("JWinger@Greendale.com");
    }

    @Then("I should get a message telling the review field is missing.")
    public void iShouldGetAMessageTellingTheReviewFieldIsMissing() {
        Assertions.assertTrue(productPage.invalidInputMessage());
    }

}
