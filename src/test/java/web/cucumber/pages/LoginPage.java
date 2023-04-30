package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void goToLoginPage() {
        webDriver.get("https://automationexercise.com/login");
    }

    public SignUpPage goToSignUpPage(){
        webDriver.findElement(By.xpath("//button[contains(text(), 'Signup')]")).click();
        return new SignUpPage(webDriver);
    }

}
