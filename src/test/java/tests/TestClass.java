package tests;

import helpers.DriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestClass {

    public WebDriver driver;

    @BeforeClass
    public void toDoBeforeAllTests() {
        driver = DriverUtil.initDriver();
    }

    @AfterClass
    public void toDoAfterAllTests() {
        driver.quit();
    }
}
