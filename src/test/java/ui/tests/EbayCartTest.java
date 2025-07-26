package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.*;
import utils.ExtentReportManager;
import utils.Log;
import com.aventstack.extentreports.ExtentTest;

public class EbayCartTest extends BaseUITest {

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void verifyItemCanBeAddedToCart() {
        ExtentTest test = ExtentReportManager.createTest("Verify Item Can Be Added To Cart");

        driver.get("https://www.ebay.com");
        test.info("Navigated to eBay");

        HomePage home = new HomePage(driver);
        SearchResultsPage results = new SearchResultsPage(driver);
        ProductPage product = new ProductPage(driver);
        CartPage cart = new CartPage(driver);

        test.info("Searching for 'book'");
        home.searchForItem("book");

        test.info("Clicking first search result");
        results.clickFirstItem();

        test.info("Clicking 'Add to Cart'");
        product.clickAddToCart();

        test.info("Validating cart is updated");
        Assert.assertTrue(cart.isCartUpdated(), "Cart is not updated");
        test.pass("Cart is updated and test passed");
    }
}
