package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import web.cucumber.pages.CartPage;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.ProductPage;
import web.cucumber.pages.ProductsPage;
import web.cucumber.util.AutomationWebsiteUtil;

import java.time.Duration;

public class VerifyNumberOfProductsInCart {
    private static WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;
    private ProductPage productPage;
    private CartPage cartPage;

    private final By adWindow = new By.ById("//u[normalize-space()='View Cart']");
    @Before("@cart_quantity")
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();

    }

    @After("@cart_quantity")
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario status =======> " + scenario.getStatus());
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driver.quit();
    }


    @Given("I start on the homepage")
    public void iStartOnTheHomepage() {
        homePage = new HomePage(driver);
    }

    @When("I navigate to the cart")
    public void iNavigateToTheCart() {
        cartPage = homePage.goToCartPage();
    }

    @And("I have an empty cart")
    public void iHaveAnEmptyCart() {

    }

    @Then("I should have {int} products in the cart")
    public void iShouldHaveProductsInTheCart(int arg0) {
        String emptyCart = cartPage.emptyCartMessage();
        Assertions.assertEquals("Cart is empty!", emptyCart);
    }


}
