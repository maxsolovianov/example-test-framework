package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.TestData.*;

public class MainPage extends BasePage {

    public static final By SEARCH_FIELD = By.xpath("(//*[@name='search_term'])");
    public static final By SEARCH_INPUT_FIELD = By.xpath("//*[@name='search_term']");
    public static final By AUTOCOMPLETE_ELEMENT = By.xpath("//*[@class='x-autocomplete__link']");
    public static final By GALLERY_TITLE_NAME = By.xpath("//*[@class='x-gallery-tile__name']");

    public static String PAGE_URL = MAIN_APPLICATION_PAGE;

    public MainPage (WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.get(PAGE_URL);
        maximizeWindow();
    }
}
