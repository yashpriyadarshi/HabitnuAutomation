package com.habitnu.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class PurchasePage {
    private final WebDriverWait wait;

    private final By totalCostLabel = By.xpath("//p[contains(text(),'Total Cost')]");
    private final By totalCostValue = By.xpath("//em");
    private final By purchaseButton = By.xpath("//input[@type='submit']");
    private final By idLocator = By.xpath("//td[contains(text(),'Id')]/following-sibling::td");

    public PurchasePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isTotalCostDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalCostLabel)).isDisplayed();
    }

    public String getTotalCost() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalCostValue)).getText();
    }

    public boolean isPriceValid() {
        String priceText = getTotalCost();
        String pricePattern = "^\\d{3,4}\\.\\d{2}$";
        return Pattern.matches(pricePattern, priceText);
    }

    public void clickPurchaseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
    }

    public boolean isOnConfirmationPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(idLocator)).isDisplayed();
    }

    public String getPurchaseId() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(idLocator)).getText();
    }
}
