package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.TestData.*;

public class CompanyRegistrationPage extends BasePage {

    public static final By LOGIN_EMAIL = By.xpath("(//input[@name='email'])[position()=1]");
    public static final By LOGIN_PASSWORD = By.xpath("(//input[@name='password'])[position()=1]");
    public static final By BUTTON_SUBMIT = By.xpath("(//button[@type='submit'])[position()=1]");
    public static final By PAGE_LOADER_MASK = By.xpath("//*[@class='x-spinner']");

    public static String PAGE_URL = JOIN_NOW_URL;

    public CompanyRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.get(PAGE_URL);
        maximizeWindow();
    }
}

