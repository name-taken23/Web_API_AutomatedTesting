package web.cucumber.util;

import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

public class AutomationWebsiteUtil {

    public static void setDriverLocation(String path) {
        System.setProperty("webdriver.chrome.driver", path);
    }

    public static ChromeDriverService getChromeDriverService(String path) {
        return new ChromeDriverService
                .Builder()
                .usingDriverExecutable(new File(path))
                .usingAnyFreePort()
                .build();
    }


}
