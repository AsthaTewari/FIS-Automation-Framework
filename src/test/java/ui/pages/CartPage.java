package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private By cartCount = By.cssSelector("span#gh-cart-n");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartUpdated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement count = wait.until(ExpectedConditions.visibilityOfElementLocated(cartCount));
        return count.getText().matches("\\\\d+");
    }
}
