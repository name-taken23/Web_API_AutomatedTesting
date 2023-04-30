package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private WebDriver webDriver;

    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterReview(String message){
        webDriver.findElement(By.xpath("//textarea[@id='review']"))
                .sendKeys(message);
    }

    public void enterName(String name){
        webDriver.findElement(By.xpath("//input[@id='name']"))
                .sendKeys(name);
    }

    public void enterEmail(String email){
        webDriver.findElement(By.xpath("//input[@id='email']"))
                .sendKeys(email);
    }

    public void clickSubmit(){
        webDriver.findElement(By.xpath("//button[@id='button-review']")).click();
    }

    public String successMessage(){
        String message = webDriver.findElement(By.xpath("//span[normalize-space()='Thank you for your review.']")).getText();
    return message;
    }

    public String checkAvailability(){
        String inStock = webDriver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]/span/b")).getText();
        return inStock;
    }

    public Boolean invalidInputMessage(){
         WebElement invalidInput = webDriver.findElement(By.cssSelector("input:invalid"));
        return invalidInput != null;

    }

}

