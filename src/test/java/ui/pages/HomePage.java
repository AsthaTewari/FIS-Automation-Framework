package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By searchInput = By.xpath("//input[@type='text']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForItem(String item) {
        driver.findElement(searchInput).sendKeys(item, Keys.ENTER);
    }
}
