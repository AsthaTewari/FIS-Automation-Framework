package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class APIScreenshotUtil {

    public static String captureAPIResponseAsScreenshot(String responseBody, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String htmlFilePath = "reports/api_html/" + testName + "_" + timestamp + ".html";
            String screenshotPath = "reports/api_screenshots/" + testName + "_" + timestamp + ".png";

            // Save API response in an HTML file
            Files.createDirectories(Paths.get("reports/api_html"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFilePath));
            writer.write("<html><body><pre>" + responseBody.replace("<", "&lt;").replace(">", "&gt;") + "</pre></body></html>");
            writer.close();

            // Set Chrome options for headless mode
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new", "--window-size=1200,800");
            WebDriver driver = new ChromeDriver(options);

            // Load the HTML and take screenshot
            File htmlFile = new File(htmlFilePath);
            driver.get("file:///" + htmlFile.getAbsolutePath());

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            Files.createDirectories(destFile.getParentFile().toPath());
            Files.copy(srcFile.toPath(), destFile.toPath());

            driver.quit();
            return screenshotPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
