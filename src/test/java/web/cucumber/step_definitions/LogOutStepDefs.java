package web.cucumber.step_definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.SignInPage;

import java.time.Duration;

public class LogOutStepDefs {
    private static HomePage homePage;
    private static SignInPage signInPage;
    private WebDriver webDriver;

    @Before
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.manage().window().maximize();

    }

    @Given("the user is on homepage")
    public void theUserIsOnHomepage() {
        homePage = new HomePage(webDriver);
    }

    @When("the user clicks the signIn button")
    public void theUserClicksTheSignInButton() {
        signInPage = homePage.goToSignInPage();
    }

    @And("the login page is displayed")
    public void theLogInPageIsDisplayed() {
        Assertions.assertEquals("https://automationexercise.com/login", signInPage.getUrl());
    }

    @When("the user enters their correct email address and password")
    public void theUserEntersTheirCorrectEmailAddressAndPassword() {
        signInPage.enterUserDetails("Example@123", "Example@123");
    }

    @And("clicks the login button")
    public void clicksTheLoginButton() {
        signInPage.clickLoginButton();

    }

    @Then("the Logged in as example1 message is displayed")
    public void theLoggedInAsExampleMessageIsDisplayed() {
        Assertions.assertEquals("Logged in as example1", signInPage.getDisplayMessage());

    }

    @Then("the user clicks the logout button")
    public void theUserClicksTheLogoutButton() {
        signInPage.clickLogOutButton();
    }


    @And("the user is navigated to the login page")
    public void theUserIsNavigatedToTheLoginPage() {
        Assertions.assertEquals("https://automationexercise.com/login", signInPage.getUrl());
    }

    @After
    public void tearDown() {
        webDriver.quit();
        webDriver.close();
    }
}
