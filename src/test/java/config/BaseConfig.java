package config;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static config.SiteConfig.*;

public class BaseConfig {
    private static EventFiringWebDriver driver;

    private WebDriver getDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty(
                        "webdriver.gecko.driver",
                        getResource("/geckodriver.exe"));
                return new FirefoxDriver();
            case "edge":
                System.setProperty(
                        "webdriver.edge.driver",
                        getResource("/MicrosoftWebDriver.exe"));
                return new InternetExplorerDriver();
            case "chrome":
                System.setProperty(
                        "webdriver.chrome.driver",
                        getResource("/chromedriver.exe"));
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Unsupported " + browser + " browser.");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * @param resource The name of the resource
     * @return Path to resource
     */
    private String getResource(String resource) {
        try {
            return Paths.get(BaseConfig.class.getResource(resource).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resource;
    }

    @BeforeTest
    @BeforeClass
    @BeforeGroups
    @Parameters({"selenium.browser"})
    public void setUp(@Optional("chrome") String browser) {
        driver = new EventFiringWebDriver(getDriver(browser));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Config.getHomeUrl());
    }

    public static WebDriver getConiguredDriver(String browser) {
        return driver;
    }

    //@AfterClass
    //@AfterGroups
    @Parameters("selenium.browser")
    public void tearDown(@Optional("chrome") String browser) {
        if (driver != null) {
            driver.quit();
        }
    }

}



