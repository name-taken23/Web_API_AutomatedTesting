package web.cucumber.pages;

import org.openqa.selenium.WebDriver;

public class VideoTutorialsPage {
    private final WebDriver webDriver;

    public VideoTutorialsPage(WebDriver driver) {
        this.webDriver=driver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }
    public String getTitle(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return webDriver.getTitle();
    }
}
