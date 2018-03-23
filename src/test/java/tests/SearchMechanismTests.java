package tests;

import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pages.MainPage;
import testOrganization.HappyPath;

import java.util.List;

import static helpers.TestData.OBJECT_COUNT;
import static helpers.TestData.SEARCH_URL;
import static pages.MainPage.*;

public class SearchMechanismTests extends TestClass {

    public MainPage mainPage;

    @BeforeClass
    public void beforeThisClass() {
        mainPage = new MainPage(driver);
    }

    @BeforeMethod
    public void beforeTest() {
        mainPage.openPage();
    }

    @AfterMethod
    public void afterTest() {
        mainPage.deleteAllCookies();
    }

    @DataProvider(name = "valuesForSearching")
    public static Object[][] valuesForSearching() {
        return new Object[][]{{"плее", "плеер"}, {"телев", "телевизоры"} };
    }

    int count = 0;

    @Category(HappyPath.class)
    @Test(dataProvider = "valuesForSearching")
    public void checkAutocomplete(String sendText, String expectedValue) throws Exception {
        mainPage.findWebElement(SEARCH_FIELD).sendKeys(sendText);
        mainPage.waitPresenceOfElementLocated(AUTOCOMPLETE_ELEMENT);
        List<WebElement> autoSuggest = mainPage.webElements(AUTOCOMPLETE_ELEMENT);

        for (WebElement webElement : autoSuggest) {
            String webElementText = webElement.getText();
            if (mainPage.isMatched("(?<=\\s|^)" + expectedValue + "(?=\\s|$)", webElementText)) {
                webElement.click();
                break;
            }
        }
        mainPage.waitPresenceOfElementLocated(SEARCH_INPUT_FIELD);
        Assert.assertEquals(mainPage.findWebElement(SEARCH_INPUT_FIELD).
                getAttribute("value"), expectedValue);
        Assert.assertTrue(mainPage.getCurrentURL().contains(SEARCH_URL));
    }

    @Category(HappyPath.class)
    @Test(dataProvider = "valuesForSearching")
    public void searchInPageList(String sendText, String expectedValue) throws Exception {
        mainPage.findWebElement(SEARCH_FIELD).sendKeys(sendText);
        mainPage.waitPresenceOfElementLocated(AUTOCOMPLETE_ELEMENT);
        List<WebElement> autoSuggest = mainPage.webElements(AUTOCOMPLETE_ELEMENT);
        for (WebElement webElement : autoSuggest) {
            String webElementText = webElement.getText();
            if (mainPage.isMatched("(?<=\\s|^)" + expectedValue + "(?=\\s|$)", webElementText)) {
                webElement.click();
                break;
            }
        }
        mainPage.waitPresenceOfElementLocated(SEARCH_INPUT_FIELD);
        List<WebElement> galleryTitles = mainPage.webElements(GALLERY_TITLE_NAME);
        if (expectedValue.equalsIgnoreCase("телевизоры")) {
            expectedValue = expectedValue.replace("ы", "");
        }
        count = 0;
        for (WebElement webElement : galleryTitles) {
            String webElementText = webElement.getText().toLowerCase();

            if (mainPage.isMatched("(?<=\\s|^)" + expectedValue + "(?=\\s|$)", webElementText)) {
                count++;
            }
        }
        Assert.assertTrue(count > OBJECT_COUNT);
    }
}
