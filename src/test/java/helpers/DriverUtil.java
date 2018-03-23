package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverUtil {

    private static FirefoxOptions takeFireFoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-headless");
        return firefoxOptions;
    }

    private static ChromeOptions takeChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        return chromeOptions;
    }

    public static WebDriver initDriver() {

        WebDriver driver = null;

        System.setProperty("browser", "chrome");

        String browserName = System.getProperty("browser");

        if (browserName != null) {

            if (browserName.toLowerCase().equals("chrome")) {

                //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

                driver = new ChromeDriver(takeChromeOptions());
            }

            if (browserName.toLowerCase().equals("firefox")) {

                //System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");

                driver = new FirefoxDriver(takeFireFoxOptions());
            }
        }
        else {
            System.out.println("No browser was specified! Firefox will be taken by default");

            driver = new FirefoxDriver();
        }
        return driver;
    }
}


