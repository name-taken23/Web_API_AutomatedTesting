package web.cucumber.pages;

import org.openqa.selenium.WebDriver;

public class TestCasesPage {
    private final WebDriver webDriver;

    public TestCasesPage(WebDriver webDriver) {
        this.webDriver=webDriver;
    }

    public String getUrl() {
       return webDriver.getCurrentUrl();
    }
    public String getTitle(){
        return webDriver.getTitle();
    }
}
