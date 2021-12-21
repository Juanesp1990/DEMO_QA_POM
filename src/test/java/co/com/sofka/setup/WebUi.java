package co.com.sofka.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebUi {
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_CHROME_DRIVER_PATH = "src/test/resources/driver/windows/chrome/chromedriver.exe";
    private static final String DEMO_QA_URL = "https://demoqa.com/automation-practice-form";

    protected WebDriver driver;

    private void setUpWebdriver(){
        System.setProperty(WEBDRIVER_CHROME_DRIVER,WEBDRIVER_CHROME_DRIVER_PATH);
    }

    private void setUpWebdriverUrl(){
        driver= new ChromeDriver();
        driver.get(DEMO_QA_URL);
    }

    protected void generalSetUp() {
        setUpWebdriver();
        setUpWebdriverUrl();
    }

    protected void quiteDriver(){
        driver.quit();
    }

    protected void maximize(){
        driver.manage().window().maximize();
    }
}
