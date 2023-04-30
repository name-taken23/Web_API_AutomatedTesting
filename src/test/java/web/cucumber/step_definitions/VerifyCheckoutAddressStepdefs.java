package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
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

public class VerifyCheckoutAddressStepdefs {

    private static WebDriver webDriver;
    private static ChromeDriverService service;
    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private LoginPage loginPage;
    private static final String DRIVER_LOCATION="src\\test\\resources\\chromedriver";

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

    @Before("@va")
    public static void setup() {
        service = AutomationWebsiteUtil.getChromeDriverService(DRIVER_LOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        //options.addArguments("headless");
        webDriver = new ChromeDriver(service, chromeOptions);
        webDriver.manage().window().maximize();
        // homePage = new HomePage(webDriver);
    }


    @Given("I go to the homepage")
    public void iGoToTheHomepage() {
        homePage = new HomePage(webDriver);
    }

    @When("I click signup or login")
    public void iClickSignupOrLogin() {
        loginPage = homePage.goToLoginPage();
    }

    @And("I fill all the sign up details to create an account")
    public void iFillAllTheSignUpDetailsToCreateAnAccount() {
        fillInSignupDetails();
    }

    @And("click submit")
    public void clickSubmit() {
        webDriver.findElement(By.tagName("button")).click();
    }

    @Then("I should see a message that the account has been created")
    public void iShouldSeeAMessageThatTheAccountHasBeenCreated() {
        String messageTitle = webDriver.findElement(By.tagName("h2")).getText();
        Assertions.assertTrue(messageTitle.contains("ACCOUNT CREATED!"));
    }

    @When("I click continue")
    public void iClickContinue() {
        webDriver.findElement(By.tagName("button")).click();
    }

    @Then("I should be logged in and should see the correct message in the navigation bar")
    public void iShouldBeLoggedInAndShouldSeeTheCorrectMessageInTheNavigationBar() {
        String loggedInText = webDriver.findElement(By.linkText("Logged in as Test")).getText();
        Assertions.assertEquals("Logged in as Test", loggedInText);
    }

    @When("I add products to the cart")
    public void iAddProductsToTheCart() {
        productsPage = homePage.goToProductsPage();
        productsPage.clickOnAddToCartOverlay("//div[@class='productinfo text-center']//p[contains(text(),'Men Tshirt')]", "body > section:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(3)");
    }

    @And("I click the Cart button")
    public void iClickTheCartButton() {
        cartPage = productsPage.clickOnViewCartButton();
    }

    @Then("I should view the cart page")
    public void iShouldViewTheCartPage() {
        Assertions.assertEquals("https://automationexercise.com/view_cart", cartPage.getUrl());
    }

    @When("I click proceed to checkout")
    public void iClickProceedToCheckout() {
        homePage.goToCartPage();
        webDriver.findElement(By.linkText("Proceed To Checkout")).click();
    }

    @Then("the billing address should be the same as registration")
    public void theBillingAddressShouldBeTheSameAsRegistration() {
        String billingAddress = webDriver.findElement(By.id("address_invoice")).getText();
        Assertions.assertTrue(billingAddress.contains("Mrs. Test TestLastName"));
        Assertions.assertTrue(billingAddress.contains("123 test street"));
        Assertions.assertTrue(billingAddress.contains("Toronto Toronto A1A 1A1"));
        Assertions.assertTrue(billingAddress.contains("Canada"));
        Assertions.assertTrue(billingAddress.contains("0123456789"));
    }

    @And("the registration address should be the same as registration")
    public void theRegistrationAddressShouldBeTheSameAsRegistration() {
        String deliveryAddress = webDriver.findElement(By.id("address_delivery")).getText();
        Assertions.assertTrue(deliveryAddress.contains("Mrs. Test TestLastName"));
        Assertions.assertTrue(deliveryAddress.contains("123 test street"));
        Assertions.assertTrue(deliveryAddress.contains("Toronto Toronto A1A 1A1"));
        Assertions.assertTrue(deliveryAddress.contains("Canada"));
        Assertions.assertTrue(deliveryAddress.contains("0123456789"));
    }

    @When("I click Delete Account")
    public void iClickDeleteAccount() {
        webDriver.findElement(By.linkText("Delete Account")).click();
    }

    @Then("I should view an message verifying the account is deleted")
    public void iShouldViewAnMessageVerifyingTheAccountIsDeleted() {
        String messageTitle = webDriver.findElement(By.tagName("h2")).getText();
        Assertions.assertTrue(messageTitle.contains("ACCOUNT DELETED!"));
    }
    @After("@va")
    public static void afterAll() {
        webDriver.close();
        webDriver.quit();
        service.stop();
    }
}
