package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import web.cucumber.pages.CartPage;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.ProductsPage;
import web.cucumber.util.RowsCounter;

import java.time.Duration;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CartPageStepdefs {
    private static WebDriver webDriver;
    private HomePage homePage;
    private CartPage cartPage;
    private ProductsPage productsPage;

    Logger logger;
    ResourceBundle resourceBundle; // for reading properties file
    String browserName; // to store browser name

    @Before("@sanity")
    public void setup() {
        logger = LogManager.getLogger(this.getClass());
        resourceBundle = ResourceBundle.getBundle("config");
        browserName = resourceBundle.getString("browser");
    }

    @After("@sanity")
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario status =======> " + scenario.getStatus());
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        webDriver.quit();
    }

    @Given("User launch browser")
    public void user_launch_browser() {
        if (browserName.equals("chrome")) {
            webDriver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            webDriver = new FirefoxDriver();
        } else if (browserName.equals("edge")) {
            webDriver = new EdgeDriver();
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("opens URL {string}")
    public void opens_url(String url) {
        webDriver.get(url);
        //webDriver.manage().window().maximize();
    }

    @When("User navigate to Products Page")
    public void user_navigate_to_products_page() {
        homePage = new HomePage(webDriver);
        productsPage = homePage.goToProductsPage();
        logger.info("Clicked on Products button");
    }

    @When("click on Add to cart on a product")
    public void click_on_add_to_cart_on_a_product() {
        productsPage.clickOnAddToCartOverlay("//body[1]/section[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/h2[1]", "a.add-to-cart");
       logger.info("Added product to the cart");
    }

    @When("press continue to checkout")
    public void press_continue_to_checkout() {
        productsPage.clickOnContinueShopping();
        logger.info("Clicked on Continue Shopping button");
    }

    @Then("User navigates to Cart Page")
    public void user_navigates_to_cart_page() {
        cartPage = homePage.goToCartPage();
        logger.info("Navigated to Cart Page");
    }

    @Then("has one item in the cart")
    public void has_one_item_in_the_cart() {
        assertEquals("1", cartPage.getTextFromField("//td[@class='cart_quantity']"));
    }

    @When("User click on Add to cart another product")
    public void userClickOnAddToCartAnotherProduct() {
        productsPage.clickOnAddToCartOverlay("//div[@class='productinfo text-center']//p[contains(text(),'Men Tshirt')]",
                "body > section:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(3)");
        logger.info("Added another product to the cart");
    }

    @And("press View Cart")
    public void pressViewCart() {
        cartPage = productsPage.clickOnViewCartButton();
        logger.info("Clicked on view cart button");
    }

    @Then("in the cart should be two items")
    public void inTheCartShouldBeTwoItems() {
        assertEquals(2, RowsCounter.countRows(webDriver));
    }
}
