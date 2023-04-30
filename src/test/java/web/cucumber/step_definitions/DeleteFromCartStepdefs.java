package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import web.cucumber.pages.CartPage;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.ProductsPage;
import web.cucumber.util.AutomationWebsiteUtil;
import web.cucumber.util.RowsCounter;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteFromCartStepdefs {

    private static WebDriver webDriver;
    private HomePage homePage;
    private CartPage cartPage;
    private ProductsPage productsPage;

    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src\\test\\resources\\chromedriver.exe";

    @Before("@kh")
    public void setup(){
        service = AutomationWebsiteUtil.getChromeDriverService(DRIVER_LOCATION);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(service, options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @After("@kh")
    public void close(){
        webDriver.close();
        webDriver.quit();
    }


    @Given("I am on the product page")
    public void iAmOnTheProductPage() {
        homePage = new HomePage(webDriver);
        productsPage = homePage.goToProductsPage();

    }

    @And("I add two items to cart")
    public void iAddTwoItemsToCart() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 600)");
        productsPage.clickOnAddToCartOverlay("//body[1]/section[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/h2[1]",
                "a.add-to-cart");
//        productsPage.clickOnContinueShopping();
//        productsPage.clickOnAddToCartOverlay("//div[@class='productinfo text-center']//p[contains(text(),'Men Tshirt')]",
//                "body > section:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(3)");
    }

    @When("I click on the cart")
    public void iClickOnTheCart() {
//        cartPage = new CartPage(webDriver);
        cartPage = productsPage.clickOnViewCartButton();
    }

    @And("I click on the remove button")
    public void iClickOnTheRemoveButton() {
        cartPage.deleteFromCart();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("the item should only be one item in the cart")
    public void theItemShouldOnlyBeOneItemInTheCart() {
        String emptyCart = cartPage.emptyCartMessage();
        System.out.println(emptyCart);
//        assertEquals(0, RowsCounter.countRows(webDriver));
        Assertions.assertEquals("Cart is empty!", emptyCart);
    }
}
