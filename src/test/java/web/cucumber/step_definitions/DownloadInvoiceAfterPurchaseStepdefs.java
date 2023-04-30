package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import web.cucumber.pages.CartPage;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.LoginPage;
import web.cucumber.pages.ProductsPage;
import web.cucumber.util.AutomationWebsiteUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DownloadInvoiceAfterPurchaseStepdefs {

    private static WebDriver webDriver;
    private static ChromeDriverService service;
    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private LoginPage loginPage;
    private static final String DRIVER_LOCATION="src\\test\\resources\\chromedriver.exe";
    private static Properties properties = new Properties();

    public void fillInSignupDetails(){
        webDriver.findElement(By.cssSelector(".signup-form input[name='name']")).sendKeys("Test");
        webDriver.findElement(By.cssSelector(".signup-form input[name='email']")).sendKeys("Test109321@gmail.com");
        webDriver.findElement(By.cssSelector(".signup-form button")).click();
        webDriver.findElement(By.id("uniform-id_gender2")).click();
        webDriver.findElement(By.id("password")).sendKeys("Test");
        Select selectDay = new Select(webDriver.findElement(By.id("days")));
        selectDay.selectByValue("1");
        Select selectMonth = new Select(webDriver.findElement(By.id("months")));
        selectMonth.selectByVisibleText("January");
        Select selectYear = new Select(webDriver.findElement(By.id("years")));
        selectYear.selectByVisibleText("1982");
        webDriver.findElement(By.id("first_name")).sendKeys("Test");
        webDriver.findElement(By.id("last_name")).sendKeys("TestLastName");
        webDriver.findElement(By.id("address1")).sendKeys("123 Test Street");
        Select selectCountry = new Select(webDriver.findElement(By.id("country")));
        selectCountry.selectByVisibleText("Canada");
        webDriver.findElement(By.id("state")).sendKeys("Toronto");
        webDriver.findElement(By.id("city")).sendKeys("Toronto");
        webDriver.findElement(By.id("zipcode")).sendKeys("A1A 1A1");
        webDriver.findElement(By.id("mobile_number")).sendKeys("0123456789");
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File is deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    @Before("@di")
    public static void setup() {
        service = AutomationWebsiteUtil.getChromeDriverService(DRIVER_LOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        //options.addArguments("headless");
        webDriver = new ChromeDriver(service, chromeOptions);
        webDriver.manage().window().maximize();
        // homePage = new HomePage(webDriver);
    }
    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        homePage = new HomePage(webDriver);
    }

    @When("I click the signup or login link")
    public void iClickTheSignupOrLoginLink() {
        loginPage = homePage.goToLoginPage();
    }

    @And("I fill all the details to create an account")
    public void iFillAllTheDetailsToCreateAnAccount() {
        fillInSignupDetails();
    }

    @And("I click submit")
    public void iClickSubmit() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 1000)");
        webDriver.findElement(By.tagName("button")).click();
    }

    @Then("I should see that the account has been created")
    public void iShouldSeeThatTheAccountHasBeenCreated() {
        String messageTitle = webDriver.findElement(By.tagName("h2")).getText();
        Assertions.assertTrue(messageTitle.contains("ACCOUNT CREATED!"));
    }

    @When("I click the continue button")
    public void iClickTheContinueButton() {
        webDriver.findElement(By.tagName("button")).click();
    }

    @Then("I should see I am logged in from the navigation bar")
    public void iShouldSeeIAmLoggedInFromTheNavigationBar() {
        String loggedInText = webDriver.findElement(By.linkText("Logged in as Test")).getText();
        Assertions.assertEquals("Logged in as Test", loggedInText);
    }

    @When("I go to the products page")
    public void iGoToTheProductsPage() {
        productsPage = homePage.goToProductsPage();
    }

    @And("I add a product to the cart")
    public void iAddAProductToTheCart() {
        productsPage.clickOnAddToCartOverlay("//div[@class='productinfo text-center']//p[contains(text(),'Men Tshirt')]", "body > section:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(3)");
    }

    @And("I click the cart button")
    public void iClickTheCartButton() {
        cartPage = productsPage.clickOnViewCartButton();
    }

    @Then("I should view the cart page and my basket")
    public void iShouldViewTheCartPageAndMyBasket() {
        Assertions.assertEquals("https://automationexercise.com/view_cart", cartPage.getUrl());
    }

    @When("I click the link to proceed to checkout")
    public void iClickTheLinkToProceedToCheckout() {
        homePage.goToCartPage();
        webDriver.findElement(By.linkText("Proceed To Checkout")).click();
    }

    @And("I click to purchase order")
    public void iClickToPurchaseOrder() {
        webDriver.findElement(By.linkText("Place Order")).click();
    }

    @And("I fill out the correct card details")
    public void iFillOutTheCorrectCardDetails() {
        webDriver.findElement(By.cssSelector("#payment-form input[name='name_on_card']")).sendKeys("Test");
        webDriver.findElement(By.cssSelector("#payment-form input[name='card_number']")).sendKeys("1234 1234 1234 1234");
        webDriver.findElement(By.cssSelector("#payment-form input[name='cvc']")).sendKeys("311");
        webDriver.findElement(By.cssSelector("#payment-form input[name='expiry_month']")).sendKeys("09");
        webDriver.findElement(By.cssSelector("#payment-form input[name='expiry_year']")).sendKeys("2028");
    }

    @And("I click proceed")
    public void iClickProceed() {
        webDriver.findElement(By.id("submit")).click();
    }

    @Then("I should view a message that my order has been successful")
    public void iShouldViewAMessageThatMyOrderHasBeenSuccessful() {
        String messageTitle = webDriver.findElement(By.tagName("h2")).getText();
        Assertions.assertTrue(messageTitle.contains("ORDER PLACED!"));
    }

    @When("I click on the button to download an invoice")
    public void iClickOnTheButtonToDownloadAnInvoice() {
        webDriver.findElement(By.linkText("Download Invoice")).click();
    }

    @Then("an invoice should be downloaded")
    public void anInvoiceShouldBeDownloaded() {
        String filePath = null;
        try {
            properties.load(new FileReader("src/main/resources/path.properties"));
            filePath = properties.getProperty("invoicepath");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean filePresent = isFileDownloaded(filePath, "invoice.txt");
        Assertions.assertEquals(true, filePresent);
    }

    @When("I click the link to delete my account")
    public void iClickTheLinkToDeleteMyAccount() {
        webDriver.findElement(By.linkText("Delete Account")).click();
    }

    @Then("I should view an message stating the account is deleted")
    public void iShouldViewAnMessageStatingTheAccountIsDeleted() {
        String messageTitle = webDriver.findElement(By.tagName("h2")).getText();
        Assertions.assertTrue(messageTitle.contains("ACCOUNT DELETED!"));
    }
    @After("@di")
    public static void afterAll() {
        webDriver.close();
        webDriver.quit();
        service.stop();
    }
}
