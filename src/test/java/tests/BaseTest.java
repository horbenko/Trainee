package tests;

import config.BrowserNames;
import config.WebDriverFactory.*;
import org.testng.annotations.*;

import static config.WebDriverInstansiator.*;

public class BaseTest {

    @Parameters({"browserName", "runMode"})
    @BeforeClass
    public void setUp(BrowserNames browserName, RunMode runMode){
        setDriver(browserName, runMode);
    }

    @AfterClass
    public void tearDown() throws Exception{
        getDriver().quit();
    }

}
