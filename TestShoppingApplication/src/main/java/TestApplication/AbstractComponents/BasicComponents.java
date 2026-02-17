package TestApplication.AbstractComponents;

import org.openqa.selenium.WebDriver;

public class BasicComponents {
    public WebDriver driver;

    public void openApplication() {
        driver = DriverManager.initializeDriver();
        driver.get(ConfigProperties.PAGE_URL);
    }
}
