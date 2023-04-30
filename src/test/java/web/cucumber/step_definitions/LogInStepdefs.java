package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
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
import web.cucumber.pages.*;
import web.cucumber.util.AutomationWebsiteUtil;

public class LogInStepdefs {
    private static WebDriver driver;

    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src\\test\\resources\\chromedriver.exe";

    private HomePage homePage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    private AccountDeletionPage accountDeletionPage;
    private AccountCreationPage accountCreationPage;

    @Before("@rl")
    public void setup(){
        service = AutomationWebsiteUtil.getChromeDriverService(DRIVER_LOCATION);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(service, options);
        driver.manage().window().maximize();
    }
    @Given("I am on the Login Page")
    public void iAmOnTheLoginPage() {
        loginPage=new LoginPage(driver);
        loginPage.goToLoginPage();
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        loginPage=new LoginPage(driver);
        loginPage.goToLoginPage();
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]")).sendKeys("room1@sparta.com");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]")).sendKeys("jira");

    }

    @And("click on the Sign In button")
    public void clickOnTheButton() {
        WebElement button = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button"));
        button.click();
    }

    @Then("I should be redirected to the application dashboard")
    public void iShouldBeRedirectedToTheApplicationDashboard() {
        WebElement loggedInLink = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a"));
        Assertions.assertTrue(loggedInLink.isDisplayed(), "Error: Logged in link is not displayed.");
    }

    @When("I enter invalid credentials And click on the Sign In button")
    public void iEnterInvalidCredentialsAndClickOnTheButton() {
        loginPage=new LoginPage(driver);
        loginPage.goToLoginPage();
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]")).sendKeys("WRONG@WRONG");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]")).sendKeys("WRONG");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button"));
        button.click();
    }

    @Then("I should see an error message indicating that the credentials are incorrect")
    public void iShouldSeeAnErrorMessageIndicatingThatTheCredentialsAreIncorrect() {
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/p"));
        Assertions.assertTrue(errorMessage.isDisplayed(), "Error: logged in when not supposed to");

    }

//    @When("I leave the username and password fields empty And click on the Sign In button")
//    public void iLeaveTheUsernameAndPasswordFieldsEmptyAndClickOnTheButton(String arg0) {
//        loginPage=new LoginPage(driver);
//        loginPage.goToLoginPage();
//        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]")).sendKeys("");
//        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]")).sendKeys("");
//        WebElement button = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button"));
//        button.click();
//    }

//    @Then("I should see an error message indicating that both fields are required")
//    public void iShouldSeeAnErrorMessageIndicatingThatBothFieldsAreRequired() {
//        WebElement errorMessages = driver.findElement(By.xpath("website wont give me the xpath for the 'both field required' text"));
//        Assertions.assertTrue(errorMessages.isDisplayed(), "Error: logged in when not supposed to");
//    }

    @After("@rl")
    public void tearDown(){
        driver.quit();


    }
}
