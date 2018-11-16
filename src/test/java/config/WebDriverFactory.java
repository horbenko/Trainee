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
        WebDriver webDriver = null;
        switch (runMode) {
            case LOCAL:
                webDriver = getLocal(browserNames);
                break;
            case REMOTE:
                webDriver = getRemote(browserNames);
                break;
        }
        return webDriver;
    }

    private WebDriver getRemote(BrowserNames browserName) {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);

        switch (browserName) {
            case CHROME:
                capability = DesiredCapabilities.chrome();
                return new RemoteWebDriver(capability);
            case FIREFOX:
                capability = DesiredCapabilities.firefox();
                return new RemoteWebDriver(capability);
            case INTERNET_EXPLORER:
                capability = DesiredCapabilities.internetExplorer();
                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                return new RemoteWebDriver(capability);
            default: throw new IllegalArgumentException("Unsupported " + browserName + " browserName.");
        }
    }

    private WebDriver getLocal(BrowserNames browserName) {
        switch (browserName) {
            case FIREFOX:
                System.setProperty(
                        "webdriver.gecko.driver",
                        getResource("/drivers/geckodriver.exe"));
                return new FirefoxDriver();
            case INTERNET_EXPLORER:
                InternetExplorerOptions options1 = new InternetExplorerOptions();
                options1.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                System.setProperty(
                        "webdriver.ie.driver",
                        getResource("/drivers/IEDriverServer.exe"));
                return new InternetExplorerDriver(options1);
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("prefs", "--disable-notifications");
                System.setProperty(
                        "webdriver.chrome.driver",
                        getResource("/drivers/chromedriver.exe"));
                return new ChromeDriver(options);
            default:
                throw new IllegalArgumentException("Unsupported browser name.");
        }
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
        LOCAL("LOCAL"),
        REMOTE("REMOTE");

        private final String webDriverFactoryMode;

        RunMode(String webDriverFactoryMode) {
            this.webDriverFactoryMode = webDriverFactoryMode;
        }

        public String getWebDriverFactoryMode() {
            return webDriverFactoryMode;
        }
    }

}
