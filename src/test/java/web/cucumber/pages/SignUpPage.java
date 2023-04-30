package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {

  private final WebDriver webDriver;

  public SignUpPage(WebDriver webDriver) {
    this.webDriver=webDriver;
  }

  public String getUrl() {
    return webDriver.getCurrentUrl();
  }

  public AccountCreationPage createAccount(){

        webDriver.findElement(By.xpath("/html/body/section/div/div/div/div/form/button")).click();
        return new AccountCreationPage(webDriver);
  }
}
