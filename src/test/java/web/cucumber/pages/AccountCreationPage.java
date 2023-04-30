package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreationPage {

  private final WebDriver webDriver;

  public AccountCreationPage(WebDriver webDriver) {
    this.webDriver=webDriver;
  }

  public String getUrl() {
    return webDriver.getCurrentUrl();
  }

  public HomePage continueToHomePage(){
    webDriver.findElement(By.xpath("/html/body/section/div/div/div/div/a")).click();
    return new HomePage(webDriver);
  }
}
