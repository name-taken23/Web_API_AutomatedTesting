package web.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {

    private WebDriver webDriver;
    private final By searchBox = new By.ByXPath("//input[@id='search_product']");
    private final By searchButton = new By.ByXPath("//button[@id='submit_search']");
    private final By viewCartButton = new By.ByXPath("//u[normalize-space()='View Cart']");
    private final By continueShoppingButton = new By.ByXPath("//button[normalize-space()='Continue Shopping']");

   // private final By viewProductButton = new By.ByXPath("//button[@id='/product_details/1']");

    public ProductsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public void waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait used to secure our operations on un synchronised pages
    public void clickOnSearchBar() {
        waitForClickable(searchBox);
        webDriver.findElement(searchBox).click();
    }

    public void searchForAndPressEntre(String keyword) {
        webDriver.findElement(searchBox).sendKeys(keyword + Keys.ENTER);
    }

    public void searchFor(String keyword) {
        webDriver.findElement(searchBox).sendKeys(keyword);
    }

    private void clickByXpath(String xpath) {
        webDriver.findElement(By.xpath(xpath)).click();
    }

    private void clickByXpath(By xpath) {
        waitForClickable(xpath);
        webDriver.findElement(xpath).click();
    }

    public void clickOnSearchButton() {
        clickByXpath(searchButton);
    }

   // public void clickOnViewProduct(){waitForClickable(viewProductButton);clickByXpath(viewProductButton);}

    public void clickOnAddToCartOverlay(String xpathOfProduct, String xpathAddButton) {
        Actions actions = new Actions(webDriver);
        WebElement add = webDriver.findElement(By.xpath(xpathOfProduct));
        actions.moveToElement(add).perform();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(xpathAddButton)));
        addToCartButton.click();
    }



//    public void clickOnAddToCartOverlay2ndProduct(String xpathOfProduct) {
//        Actions actions = new Actions(webDriver);
//        WebElement add = webDriver.findElement(By.xpath(xpathOfProduct));
//        actions.moveToElement(add).perform();
//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
//        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[3]//div[1]//div[1]//div[2]//div[1]//a[1]")));
//        addToCartButton.click();
//    }

    public void clickOnContinueShopping() {
        webDriver.findElement(continueShoppingButton).click();
    }

    public CartPage clickOnViewCartButton() {
        waitForClickable(viewCartButton);
        webDriver.findElement(viewCartButton).click();
        return new CartPage(webDriver);
    }

    public ProductPage goToProductPage(){
        webDriver.findElement(By.xpath("(//a[contains(text(),'View Product')])[3]")).click();
        return new ProductPage(webDriver);
    }
}
