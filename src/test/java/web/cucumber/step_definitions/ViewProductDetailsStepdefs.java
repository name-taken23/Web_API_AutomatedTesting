package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.cucumber.pages.CartPage;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.ProductPage;
import web.cucumber.pages.ProductsPage;

import java.time.Duration;

public class ViewProductDetailsStepdefs {
    private static WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;
    private ProductPage productPage;
    private CartPage cartPage;

    private final By adWindow = new By.ById("//u[normalize-space()='View Cart']");
    @Before("@prod_details")
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

    @After("@prod_details")
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario status =======> " + scenario.getStatus());
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driver.quit();
    }
    @Given("I am on the automation exercise homepage")
    public void iAmOnTheAutomationExerciseHomepage() {
        homePage = new HomePage(driver);
    }

    @When("I click on the products page")
    public void iClickOnTheProductsPage() {
        productsPage = homePage.goToProductsPage();
    }


    @Then("I navigate to products page")
    public void iNavigateToProductsPage() {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0, 600)");
        productPage = productsPage.goToProductPage();
   //     productsPage.clickOnViewProduct();
       /* try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        */
     //     Assertions.assertEquals("https://automationexercise.com/product_details/3", productsPage.getUrl());

        String expectedElementText = productPage.checkAvailability();
        String actualElementText = "St";
        //issue with HTML
        Assert.assertEquals(actualElementText, expectedElementText);
//using getText method the retrieve the text of the element


    }



}
