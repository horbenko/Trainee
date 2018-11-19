package tests;

import config.BrowserNames;
import config.TestListener;
import config.WebDriverFactory.*;
import org.testng.annotations.*;

import static config.WebDriverInstansiator.*;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeClass
    @Parameters({"browserName", "runMode"})
    public void setUp(String browserName, String runMode){
        BrowserNames castedBrowserName = BrowserNames.valueOf(browserName.toUpperCase());
        RunMode castedRunMode = RunMode.valueOf(runMode.toUpperCase());
        setDriver(castedBrowserName, castedRunMode);
    }

    @AfterClass
    public void tearDown() throws Exception{
        getDriver().quit();
    }
}
