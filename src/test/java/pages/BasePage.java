package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePage {

    public WebDriver driver;

    public static String PAGE_URL;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PAGE_URL);
    }

    public void closeBrowserWindow() { driver.close(); }

    public void quitBrowser() { driver.quit(); }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public void openLink(String url) {
        driver.get(url);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle() {
        return driver.getTitle();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void goForward() {
        driver.navigate().forward();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void pr(String text) {
        System.out.println(text);
    }

    public List webElements(By by) {
        List<WebElement> list = driver.findElements(by);
        return list;
    }

    public boolean isMatched(String regex, String string) {
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public void waitUntilElementGetsInvisible(WebElement elementTobeInvisible) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOf(elementTobeInvisible));
    }

    public void waitPresenceOfElementLocated(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 20, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated((by)));
    }

    public void waitVisibilityOf(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20, 500);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement findWebElement(By by) {

        WebElement webElement;

        try {
            webElement = new WebDriverWait(driver, 10, 500).
                    until(ExpectedConditions.visibilityOf(driver.findElement(by)));

        } catch (org.openqa.selenium.NoSuchElementException exception) {

            System.out.println("ERROR: The element " + by.toString() + " was not found!");
            return null;
        }
        return webElement;
    }

    // for debugging
    public void waitForAjax() throws InterruptedException {
        while (true)
        {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor)driver).
                    executeScript("return jQuery.active == 0");
            if (ajaxIsComplete){
                break;
            }
            Thread.sleep(100);
        }
    }

    // for debugging
    public void waitForReadyState() throws InterruptedException {
        while (true)
        {
            Boolean readyIsComplete = (Boolean) ((JavascriptExecutor)driver).
                    executeScript("return document.readyState").equals("complete");
            if (readyIsComplete){
                break;
            }
            Thread.sleep(100);
        }
    }
}
