package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ContactUsPage {
    private final WebDriver webDriver;

    public ContactUsPage(WebDriver driver) {
        this.webDriver=driver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void clickContactUsButton() {
        webDriver.findElement(By.xpath("//a[normalize-space()='Contact us']")).click();
    }

    public String getInTouchMessage() {
       return webDriver.findElement(By.xpath("//h2[normalize-space()='Get In Touch']")).getText();
    }

    public void enterMessageDetails(String name, String email, String subject, String message) {
        webDriver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(name);
        webDriver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
        webDriver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys(subject);
        webDriver.findElement(By.xpath(" //textarea[@id='message']")).sendKeys(message);
       // System.out.println("Checking code");
    }


    public void clickChooseFileButton() {
        //System.out.println("Checking code");

        webDriver.findElement(By.xpath("//input[@name='upload_file']")).click();

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }

    public void clickTheSubmitButton() {
        webDriver.findElement(By.xpath("//input[@name='submit']")).click();
    }

    public void clickTheOkButton() {
        webDriver.switchTo().alert().accept();
    }

    public String successMessage() {
        return webDriver.findElement(By.xpath("//div[@class='status alert alert-success']")).getText();

    }
}
