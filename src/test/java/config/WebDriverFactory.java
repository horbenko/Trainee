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
            default:
                return webDriver = getLocal(browserNames);
        }
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
            case IE:
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
            case IE:
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                System.setProperty(
                        "webdriver.ie.driver",
                        getResource("/drivers/IEDriverServer.exe"));
                return new InternetExplorerDriver(ieOptions);
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("prefs", "--disable-notifications");
                chromeOptions.addArguments("start-maximized");

                System.out.println(getResource("chromedriver"));

                System.setProperty(
                        "webdriver.chrome.driver",
                        "/usr/bin/chromedriver");
                return new ChromeDriver(chromeOptions);
            default:
                throw new IllegalArgumentException("Unsupported browser name.");
        }
    }

    public void getOS() {
        System.getProperty("os.name");
        System.out.println(System.getProperty("os.name"));
    }

    /**
     * @param resource The name of the resource
     * @return Path to resource
     */
    private String getResource(String resource) {
        try {
            System.out.println(Paths.get(WebDriverFactory.class.getResource(resource).toURI()).toFile().getPath());
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
