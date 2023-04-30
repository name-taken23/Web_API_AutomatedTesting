package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private WebDriver webDriver;

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    private void clickByXpath(By xpath) {
        webDriver.findElement(xpath);
    }

    public String getTextFromField(String xpath) {
        return webDriver.findElement(By.xpath(xpath)).getText();
    }

    private void clickByXpath(String xpath) {
        webDriver.findElement(By.xpath(xpath)).click();
    }

    public void deleteFromCart() {
        webDriver.findElement(By.xpath("(//a[@class='cart_quantity_delete'])[1]")).click();
    }

    public String emptyCartMessage(){
        String output = webDriver.findElement(By.xpath("//b[normalize-space()='Cart is empty!']")).getText();
        return output;
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
