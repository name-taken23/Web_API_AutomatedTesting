package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private WebDriver webDriver;
    private final By productsPage = new By.ByXPath("//a[@href='/products']");
    private final By cartPage = new By.ByXPath("//body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]");
    private final By loginPage = new By.ByXPath("//a[normalize-space()='Signup / Login']");
    private final By testCasesPage = new By.ByXPath("//a[normalize-space()='Test Cases']");
    private final By contactUsPage = new By.ByXPath("//a[normalize-space()='Contact us']");


    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        goToHomePage();
    }

    private void goToHomePage() {
        webDriver.get("https://automationexercise.com/");
    }

    public ProductsPage goToProductsPage() {
        webDriver.findElement(productsPage).click();
        Actions actions = new Actions(webDriver);
        actions.moveByOffset(0, 0).click().perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ProductsPage(webDriver);
    }

    public CartPage goToCartPage() {
        webDriver.findElement(cartPage).click();
        return new CartPage(webDriver);
    }
    public TestCasesPage goToTestCasesPage(){
        webDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[5]/a")).click();
        return new TestCasesPage(webDriver);
    }
    public APITestingPage goToAPITestingPage(){
        webDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[6]/a")).click();
        return new APITestingPage(webDriver);
    }
    public VideoTutorialsPage goToVideoTutorialsPage(){
        webDriver.findElement(By.xpath("//a[normalize-space()='Video Tutorials']")).click();
        return new VideoTutorialsPage(webDriver);
    }
    public ContactUsPage goToContactUsPage(){
        webDriver.findElement(By.xpath("//a[normalize-space()='Contact us']")).click();
        return new ContactUsPage(webDriver);
    }

    public SignInPage goToSignInPage(){
        webDriver.findElement(By.xpath("//a[normalize-space()='Signup / Login']")).click();
        return new SignInPage(webDriver);
    }
    public LoginPage goToLoginPage() {
        webDriver.findElement(loginPage).click();
        return new LoginPage(webDriver);
    }

    public AccountDeletionPage goToDeletionPage(){
        webDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[5]/a")).click();
        return new AccountDeletionPage(webDriver);
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getContentOfFooterElementH2(){

        return webDriver.findElement(By.cssSelector(".single-widget h2")).getText();
    }

    public void enterEmailInFooter(String Email){

        webDriver.findElement(By.cssSelector(".searchform input")).sendKeys(Email);
    }

    public void clickArrowSubmitButton(){
        webDriver.findElement(By.cssSelector(".searchform button i")).click();
    }


}
