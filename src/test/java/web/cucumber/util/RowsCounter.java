package web.cucumber.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RowsCounter {
    // For a single table!
    public static int countRows(WebDriver webDriver) {
        return webDriver.findElements(By.tagName("tr")).size() - 1; //-1 because of description row
    }

}
