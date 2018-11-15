package config;

import config.WebDriverInstansiator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseConfigGrid {

    @Parameters({"browserName"})
    @BeforeClass
    public void setUp(@Optional String browserName){
        WebDriverInstansiator.setDriver(browserName);
    }

    @AfterClass
    public void tearDown() throws Exception{
        WebDriverInstansiator.getDriver().quit();
    }

}
