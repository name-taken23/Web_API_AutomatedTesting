package web.cucumber.pages;

import org.openqa.selenium.WebDriver;

public class APITestingPage {
    private final WebDriver webDriver;

    public APITestingPage(WebDriver webDriver) {
        this.webDriver=webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }
}
