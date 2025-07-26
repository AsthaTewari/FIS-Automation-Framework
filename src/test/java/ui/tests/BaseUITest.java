package ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;
import com.aventstack.extentreports.MediaEntityBuilder;

public class BaseUITest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                ExtentReportManager.getTest().fail("Test Failed: Screenshot attached",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void afterSuite() {
        ExtentReportManager.flush();
    }
}
