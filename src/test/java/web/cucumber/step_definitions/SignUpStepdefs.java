package web.cucumber.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import web.cucumber.pages.AccountCreationPage;
import web.cucumber.pages.AccountDeletionPage;
import web.cucumber.pages.SignUpPage;
import web.cucumber.pages.HomePage;
import web.cucumber.pages.LoginPage;
import web.cucumber.util.AutomationWebsiteUtil;

public class SignUpStepdefs {
  private static WebDriver driver;

  private static ChromeDriverService service;
  private static final String DRIVER_LOCATION = "src\\test\\resources\\chromedriver.exe";

  private HomePage homePage;
  private LoginPage loginPage;
  private SignUpPage signUpPage;

  private AccountDeletionPage accountDeletionPage;
  private AccountCreationPage accountCreationPage;

  @Before("@jr")
  public void setup(){
    service = AutomationWebsiteUtil.getChromeDriverService(DRIVER_LOCATION);
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(service, options);
    driver.manage().window().maximize();
  }
  @Given("I am on the main page")
  public void iAmOnTheMainPage() {
    homePage=new HomePage(driver);
  }

  @When("I click on the Signup link")
  public void iClickOnTheSignupLink() {
    loginPage=homePage.goToLoginPage();
  }

  @Then("I should go to the Signup page")
  public void iShouldGoToTheSignupPage() {
    Assertions.assertEquals("https://automationexercise.com/login", loginPage.getUrl());
  }



  @Given("I am on the login page")
  public void iAmOnTheLoginPage() {
    loginPage=new LoginPage(driver);
    loginPage.goToLoginPage();
  }

  @When("I enter details to sign up")
  public void iEnterDetailsToSignUp() {
    driver.findElement(By.name("name")).sendKeys("test");
    driver.findElement(By.xpath("/html/body/section/div/div/div[3]/div/form/input[3]")).sendKeys("room1project2@test.com");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Then("I should go to the sign up page")
  public void iShouldGoToTheSignUpPage() {
    signUpPage =loginPage.goToSignUpPage();
    Assertions.assertEquals("https://automationexercise.com/signup", signUpPage.getUrl());
  }


//  @Given("I have entered details to sign up on the login page and clicked signup")
//  public void iHaveEnteredDetailsToSignUpOnTheLoginPageAndClickedSignup() {
//    loginPage=new LoginPage(driver);
//    loginPage.goToLoginPage();
//    driver.findElement(By.name("name")).sendKeys("test");
//    driver.findElement(By.xpath("/html/body/section/div/div/div[3]/div/form/input[3]")).sendKeys("room1project@test.com");
//
//    try {
//      Thread.sleep(2000);
//      signUpPage =loginPage.goToSignUpPage();
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @When("I enter details on the sign up page and click create account")
//  public void iEnterDetailsOnTheSignUpPageAndClickCreateAccount() {
//    driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("t");
//    driver.findElement(By.xpath("//*[@id=\"first_name\"]")).sendKeys("testName");
//    driver.findElement(By.xpath("//*[@id=\"last_name\"]")).sendKeys("testLastName");
//    driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys("t");
//    driver.findElement(By.xpath("//*[@id=\"state\"]")).sendKeys("t");
//    driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("t");
//    driver.findElement(By.xpath("//*[@id=\"zipcode\"]")).sendKeys("t");
//    driver.findElement(By.xpath("//*[@id=\"mobile_number\"]")).sendKeys("1");
//    JavascriptExecutor jse = (JavascriptExecutor)driver;
//    jse.executeScript("window.scrollTo(0, 1000)");
//
//  }
//
//  @Then("I should be taken to the account creation page")
//  public void iShouldBeTakenToTheAccountCreationPage() {
//    accountCreationPage = signUpPage.createAccount();
//    Assertions.assertEquals("https://automationexercise.com/account_created", accountCreationPage.getUrl());
//  }

  @Given("I have signed up")
  public void iHaveSignedUp() {
    loginPage=new LoginPage(driver);
    loginPage.goToLoginPage();
    driver.findElement(By.name("name")).sendKeys("test");
    driver.findElement(By.xpath("/html/body/section/div/div/div[3]/div/form/input[3]")).sendKeys("room1project2@test.com");
    try {
      Thread.sleep(500);
      signUpPage =loginPage.goToSignUpPage();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("t");
    driver.findElement(By.xpath("//*[@id=\"first_name\"]")).sendKeys("testName");
    driver.findElement(By.xpath("//*[@id=\"last_name\"]")).sendKeys("testLastName");
    driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys("t");
    driver.findElement(By.xpath("//*[@id=\"state\"]")).sendKeys("t");
    driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("t");
    driver.findElement(By.xpath("//*[@id=\"zipcode\"]")).sendKeys("t");
    driver.findElement(By.xpath("//*[@id=\"mobile_number\"]")).sendKeys("1");
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("window.scrollTo(0, 1000)");
    try {
      Thread.sleep(500);
      accountCreationPage = signUpPage.createAccount();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    try {
      Thread.sleep(500);
      homePage = accountCreationPage.continueToHomePage();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  @When("I click on delete account")
  public void iClickOnDeleteAccount() {
    accountDeletionPage = homePage.goToDeletionPage();
  }

  @Then("my account should be delete")
  public void myAccountShouldBeDelete() {
    Assertions.assertEquals("https://automationexercise.com/delete_account", accountDeletionPage.getUrl());
  }

  @Given("I have an existing account and I am trying to sign up again")
  public void iHaveAnExistingAccountAndIAmTryingToSignUpAgain() {
    homePage = new HomePage(driver);
    loginPage = homePage.goToLoginPage();
    driver.findElement(By.name("name")).sendKeys("t");
    driver.findElement(By.xpath("/html/body/section/div/div/div[3]/div/form/input[3]")).sendKeys("room1project3@test.com");
  }

  @When("I try to sign up with the same details")
  public void iTryToSignUpWithTheSameDetails() {
    try {
      Thread.sleep(2000);
      driver.findElement(By.xpath("//button[contains(text(), 'Signup')]")).click();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Then("I should get Email Address already exists message")
  public void iShouldGetEmailAddressAlreadyExistsMessage() {
    Assertions.assertEquals("Email Address already exist!",driver.findElement(By.xpath("/html/body/section/div/div/div[3]/div/form/p")).getText());
  }

  @After("@jr")
  public void tearDown(){
    driver.quit();
  }
}
