package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
    private WebDriver driver;
    private By firstItem = By.cssSelector(".s-item__title");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstItem() {
        driver.findElements(firstItem).get(0).click();
    }
}
