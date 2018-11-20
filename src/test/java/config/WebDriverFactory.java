package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class WebDriverFactory {

    public WebDriver getWebDriver(BrowserNames browserNames, RunMode runMode) {
        WebDriver webDriver;
        switch (runMode) {
            case REMOTE:
                return webDriver = getRemote(browserNames);
            case LOCAL:
            default:
                return webDriver = getLocal(browserNames);
        }
    }

    private WebDriver getRemote(BrowserNames browserName) {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);
        switch (browserName) {
            case FIREFOX:
                capability = DesiredCapabilities.firefox();
                return new RemoteWebDriver(capability);
            case IE:
                capability = DesiredCapabilities.internetExplorer();
                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                return new RemoteWebDriver(capability);
            case CHROME:
            default:
                capability = DesiredCapabilities.chrome();
                return new RemoteWebDriver(capability);
        }
    }

    private WebDriver getLocal(BrowserNames browserName) {
        WebDriver webDriver;
        if (getOS().contains("Window")) {
            return webDriver = getForWindows(browserName);
        } else
            return webDriver = getForUnix(browserName);
    }

    private WebDriver getForWindows(BrowserNames browserName) {
        switch (browserName) {
            case FIREFOX:
                System.setProperty(
                        "webdriver.gecko.driver",
                        getResource("/drivers/geckodriver.exe"));
                return new FirefoxDriver();
            case IE:
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                System.setProperty(
                        "webdriver.ie.driver",
                        getResource("/drivers/IEDriverServer.exe"));
                return new InternetExplorerDriver(ieOptions);
            case CHROME:
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("prefs", "--disable-notifications");
                chromeOptions.addArguments("start-maximized");
                System.setProperty(
                        "webdriver.chrome.driver",
                        getResource("/drivers/chromedriver.exe"));
                return new ChromeDriver(chromeOptions);
        }
    }

    private WebDriver getForUnix(BrowserNames browserName) {
        switch (browserName) {
            case FIREFOX:
                System.setProperty(
                        "webdriver.gecko.driver",
                        getResource("/drivers/geckodriver"));
                return new FirefoxDriver();
            case IE: throw new IllegalArgumentException("Not supported browser for Unix/Linux OS.");
            case CHROME:
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("prefs", "--disable-notifications");
                chromeOptions.addArguments("start-maximized");
                System.setProperty(
                        "webdriver.chrome.driver",
                        getResource("/drivers/chromedriver"));
                return new ChromeDriver(chromeOptions);
        }
    }

    private String getOS() {
        return System.getProperty("os.name");
    }

    /**
     * @param resource The name of the resource
     * @return Path to resource
     */
    private String getResource(String resource) {
        try {
            return Paths.get(WebDriverFactory.class.getResource(resource).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resource;
    }

    public enum RunMode {
        LOCAL("local"),
        REMOTE("remote");

        private final String webDriverFactoryMode;

        RunMode(String webDriverFactoryMode) {
            this.webDriverFactoryMode = webDriverFactoryMode;
        }

        public String getWebDriverFactoryMode() {
            return webDriverFactoryMode;
        }
    }

}
