package config;

import config.WebDriverFactory.RunMode;
import org.openqa.selenium.WebDriver;

public class WebDriverInstansiator {

    private static InheritableThreadLocal<WebDriver> webDriver = new InheritableThreadLocal<>();
    private static WebDriverFactory factory;

    public static void setDriver(BrowserNames browserName, RunMode runMode){
        factory = new WebDriverFactory();
        webDriver.set(factory.getWebDriver(browserName, runMode));
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

}
