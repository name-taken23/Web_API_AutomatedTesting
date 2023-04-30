package web.cucumber.step_definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.cucumber.pages.ContactUsPage;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.SignInPage;

import java.time.Duration;


public class ContactUsFormStepDefs {

    private static ContactUsPage contactUsPage;
    private static HomePage homePage;
    private static ContactUsPage signInPage;
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

    @Given("the user navigates to homepage")
    public void theUserNavigatesToHomepage() {
        homePage = new HomePage(webDriver);
    }


    @When("the user clicks the Contact Us button")
    public void theUserClicksContactUsButton() {
      contactUsPage = homePage.goToContactUsPage();

    }

    @And("the GET IN TOUCH section is displayed")
    public void theGetInTouchSectionIsDisplayed() {
        Assertions.assertEquals("GET IN TOUCH", contactUsPage.getInTouchMessage());
    }

    @Then("the user enters their name, email, subject, and message")
    public void theUserEntersTheirNameEmailSubjectAndMessage() {
        contactUsPage.enterMessageDetails("Example123","Example@123","Example Hello",
                "Example Hello");
    }

    @And("the user click on choose file button")
    public void theUserClickOnChooseFileButton() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, 100)");
        contactUsPage.clickChooseFileButton();
    }


    @When("the user clicks the Submit button")
    public void theUserClicksTheSubmitButton() {
        contactUsPage.clickTheSubmitButton();
    }

    @And("the user clicks the OK button")
    public void theUserClicksTheOkButton() {
        contactUsPage.clickTheOkButton();
    }

    @Then("the message Success! Your details have been submitted successfully is visible")
    public void theMessageSuccessYourDetailsHaveBeenSubmittedSuccessfullyIsVisible() {
            Assertions.assertEquals("Success! Your details have been submitted successfully."
                    ,contactUsPage.successMessage());

    }

    @And("the user is navigated back the login page")
    public void theUserIsNavigatedBackToTheLoginPage() {
        Assertions.assertEquals("https://automationexercise.com/login", signInPage.getUrl());
    }

    @After
    public void tearDown() {
        webDriver.quit();
        webDriver.close();
    }
}
