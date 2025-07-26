package api.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Log;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;
import utils.APIScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.util.Map;

public class CoinGeckoApiTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyCoinGeckoResponse() {
        ExtentTest test = ExtentReportManager.createTest("Verify CoinGecko API Response");

        Log.info("Sending GET request to CoinGecko API...");
        test.info("Sending GET request to CoinGecko API...");
        Response response = RestAssured.get("https://api.coingecko.com/api/v3/coins/bitcoin");

        try {
            Log.info("Verifying response status code...");
            test.info("Verifying response status code...");
            Assert.assertEquals(response.statusCode(), 200);

            JsonPath json = response.jsonPath();
            Log.info("Parsing market data from response...");
            test.info("Parsing market data from response...");
            Map<String, Object> marketData = json.getMap("market_data");

            Assert.assertNotNull(marketData.get("market_cap"), "Market cap is missing");
            Assert.assertNotNull(marketData.get("total_volume"), "Total volume is missing");

            Log.info("Checking price change and homepage URL...");
            test.info("Checking price change and homepage URL...");
            Map<String, Float> priceChange = json.getMap("market_data.price_change_percentage_24h_in_currency");
            Assert.assertTrue(priceChange.containsKey("usd"), "USD price change not found");

            String homepage = json.getString("links.homepage[0]");
            Assert.assertNotNull(homepage);
            Assert.assertFalse(homepage.isEmpty(), "Homepage URL should not be empty");

            test.pass("All assertions passed for CoinGecko API.");
        } catch (AssertionError e) {
            String screenshotPath = APIScreenshotUtil.captureAPIResponseAsScreenshot(response.prettyPrint(), "CoinGeckoApiTest");
            if (screenshotPath != null) {
                test.fail("Assertion failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                test.fail("Assertion failed: " + e.getMessage());
            }
            throw e;
        }
    }
}
