package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import web.cucumber.pages.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import web.cucumber.pages.HomePage;
import java.time.Duration;
import java.util.ResourceBundle;


import static org.junit.jupiter.api.Assertions.assertTrue;




public class PlaceOrderWithRegiStepdefs {

    ResourceBundle resourceBundle; // for reading properties file
    String browserName; // to store browser name

    private static WebDriver driver;

    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src\\test\\resources\\chromedriver.exe";

    private HomePage homePage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private AccountDeletionPage accountDeletionPage;
    private AccountCreationPage accountCreationPage;

    // Account Details
    private static String userName = "Amaan";
    private static String email = "amaan05@gmail.com";
    private static String password = "1234";
    private static String firstName = "amaan";
    private static String lastName = "dhaal";
    private static String address = "58 lala land";
    private static String state = "polala";
    private static String city = "mannyyyy";
    private static String zipCode = "CC7 1A9";
    private static String mobileNumber = "987465343221";


    @Before("@sanity")
    public void setup() {

        resourceBundle = ResourceBundle.getBundle("config");
        browserName = resourceBundle.getString("browser");
    }


    @Given("I launch the browser")
    public void iLaunchTheBrowser() {
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @And("I navigate to the homepage")
    public void iNavigateToTheHomepage() {
        driver.get("https://automationexercise.com");
        homePage = new HomePage(driver);

    }


    @When("I add products to cart")
    public void iAddProductsToCart() {
        driver.findElement(By.cssSelector("a.add-to-cart")).click();
    }

    @And("I press the cart button")
    public void iPressTheCartButton() {
        driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a")).click();
    }

    @And("I click Proceed To Checkout")
    public void iClickProceedToCheckout() {
        driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();
    }

    @And("I click the Register button")
    public void iClickTheRegisterButton() {
        driver.findElement(By.xpath("//*[@id=\"checkoutModal\"]/div/div/div[2]/p[2]/a")).click();
    }

    @When("I fill all the details in Signup and create an account")
    public void iFillAllTheDetailsInSignupAndCreateAnAccount() {
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]")).sendKeys(userName);
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
    }

    @And("I fill in the rest of the other details in the page")
    public void iFillInTheRestOfTheOtherDetailsInThePage() {
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"first_name\"]")).sendKeys(firstName);
        driver.findElement(By.xpath("//*[@id=\"last_name\"]")).sendKeys(lastName);
        driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys(address);
        driver.findElement(By.xpath("//*[@id=\"state\"]")).sendKeys(state);
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(city);
        driver.findElement(By.xpath("//*[@id=\"zipcode\"]")).sendKeys(zipCode);
        driver.findElement(By.xpath("//*[@id=\"mobile_number\"]")).sendKeys(mobileNumber);
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/form/button")).click();
    }

    @Then("I verify ACCOUNT CREATED! is displayed")
    public void iVerifyACCOUNTCREATEDIsDisplayed() {
    assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b")).isDisplayed(), "Account created is not displayed :<");

    }

    @And("I click the Continue button")
    public void iClickTheContinueButton() {
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
    }

    @Then("I verify Logged in as username is displayed at the top")
    public void iVerifyLoggedInAsUsernameIsDisplayedAtTheTop() {
        assertTrue(driver.findElement(By.xpath("//*[contains(text(), '" + userName + "')]")).isDisplayed(), "Username is not displayed at the top of the page :<");

    }

    @And("I click the Cart button there")
    public void iClickTheCartButtonThere() {
        homePage.goToCartPage();
    }

    @And("I click the Proceed To Checkout button")
    public void iClickTheProceedToCheckoutButton() {
        driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();
    }


    @When("I enter a description in the comment text area")
    public void iEnterADescriptionInTheCommentTextArea() {
        driver.findElement(By.xpath("//*[@id=\"ordermsg\"]/textarea")).sendKeys("This is my delivery");
    }

    @And("I click the Place Order button")
    public void iClickThePlaceOrderButton() {
        driver.findElement(By.xpath("//*[@id=\"cart_items\"]/div/div[7]/a")).click();
    }

    @And("I enter payment details: Name on Card, Card Number, CVC, Expiration date")
    public void iEnterPaymentDetailsNameOnCardCardNumberCVCExpirationDate() {
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[1]/div/input")).sendKeys(firstName + " " + lastName);
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[2]/div/input")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[1]/input")).sendKeys("474");
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[2]/input")).sendKeys("07");
        driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[3]/input")).sendKeys("2024");
    }

    @And("I click the Pay and Confirm Order button")
    public void iClickThePayAndConfirmOrderButton() {
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
    }

    @And("I click the Delete Account button")
    public void iClickTheDeleteAccountButton() {
        homePage.goToDeletionPage();
    }

    @Then("I verify ACCOUNT DELETED! is displayed")
    public void iVerifyACCOUNTDELETEDIsDisplayed() {
        assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b")).isDisplayed(), "The account hasn't deleted successfully :<");

    }



}
