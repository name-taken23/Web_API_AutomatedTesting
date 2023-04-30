package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    private final WebDriver webDriver;

    public SignInPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void enterUserDetails(String email, String password) {
        webDriver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
        webDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);

    }

    public void clickLoginButton() {
        webDriver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }

    public String getDisplayMessage() {
      return webDriver.findElement(By.xpath(" //li[10]//a[1]")).getText();
    }

    public void clickLogOutButton() {
        webDriver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
    }
}
