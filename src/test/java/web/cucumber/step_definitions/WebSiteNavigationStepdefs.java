package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import web.cucumber.pages.*;
import web.cucumber.util.AutomationWebsiteUtil;

import java.time.Duration;

public class WebSiteNavigationStepdefs {

    private static WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private ContactUsPage contactUsPage;
    private SignInPage signInPage;
    private TestCasesPage testCasesPage;
    private VideoTutorialsPage videoTutorialsPage;
    private APITestingPage apiTestingPage;

    private static final String DRIVER_LOCATION="src\\test\\resources\\chromedriver";
    private static ChromeDriverService service;




    @Before("@webNav")
    public void setup(){
        service = AutomationWebsiteUtil.getChromeDriverService(DRIVER_LOCATION);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();

    }
    @Given("I am on the automation exercise Website")
    public void iAmOnTheAutomationExerciseWebsite() {
        homePage=new HomePage(driver);


    }

    @When("I click on the products link")
    public void iClickOnTheProductsLink() {
        productsPage=homePage.goToProductsPage();

    }

    @Then("I will go to the products Page")
    public void iWillGoToTheProductsPage() {
      Assertions.assertEquals("https://automationexercise.com/products",productsPage.getUrl());
      // Assertions.assertEquals( "Automation Exercise",productsPage.getTitle());
    }

    @When("I click on the cart link")
    public void iClickOnTheCartLink() {
        cartPage=homePage.goToCartPage();
    }

    @Then("I will go to the cart page")
    public void iWillGoToTheCartPage() {
        Assertions.assertEquals("https://automationexercise.com/view_cart",cartPage.getUrl());
    }


    @When("I click on the Signup and Login link")
    public void iClickOnTheSignupAndLoginLink() {
        signInPage=homePage.goToSignInPage();
    }

    @Then("I will go to the sign in page")
    public void iWillGoToTheSignInPage() {
        Assertions.assertEquals("https://automationexercise.com/login",signInPage.getUrl());
    }

    @When("I click on the Test cases link")
    public void iClickOnTheTestCasesLink() {
        try {
            testCasesPage=homePage.goToTestCasesPage();
            Thread.sleep(2000);
            WebElement iframe = driver.findElement(By.cssSelector("iframe#aswift_9"));
            driver.switchTo().frame(iframe);
            Actions builder = new Actions(driver);
            WebElement html = driver.findElement(By.tagName("body"));
            builder.moveToElement(html, 10, 25).click().build().perform();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Then("I will go to the Test cases page")
    public void iWillGoToTheTestCasesPage() {
      //  Assertions.assertEquals("Automation Exercise",testCasesPage.getTitle());
        Assertions.assertEquals("https://automationexercise.com/test_cases",testCasesPage.getUrl());
    }

    @When("I click on the API Testing link")
    public void iClickOnTheAPITestingLink() {
       try{
        apiTestingPage=homePage.goToAPITestingPage();
        Thread.sleep(2000);
        WebElement iframe = driver.findElement(By.cssSelector("iframe#aswift_9"));
        driver.switchTo().frame(iframe);
        Actions builder = new Actions(driver);
        WebElement html = driver.findElement(By.tagName("body"));
        builder.moveToElement(html, 0, 0).click().build().perform();
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

}

    @Then("I will go to the API Testing page")
    public void iWillGoToTheAPITestingPage() {
        Assertions.assertEquals("https://automationexercise.com/api_list",apiTestingPage.getUrl());
    }

    @When("I click on the video tutorials link")
    public void iClickOnTheVideoTutorialsLink() {
        videoTutorialsPage=homePage.goToVideoTutorialsPage();
    }

    @Then("I will go to the Video Tutorials page")
    public void iWillGoToTheVideoTutorialsPage() {
        Assertions.assertEquals("https://www.youtube.com/c/AutomationExercise",videoTutorialsPage.getTitle());
    }

    @When("I click on the contact us link")
    public void iClickOnTheContactUsLink() {
        contactUsPage=homePage.goToContactUsPage();
    }

    @Then("I will go to the contact us page")
    public void iWillGoToTheContactUsPage() {
        Assertions.assertEquals("https://automationexercise.com/contact_us",contactUsPage.getUrl());
    }


    @After("@webNav")
    public void tearDown(){
        driver.quit();
    }


//    @When("I click on the Signup\\/Login link")
//    public void iClickOnTheSignupLoginLink() {
//    }
}
