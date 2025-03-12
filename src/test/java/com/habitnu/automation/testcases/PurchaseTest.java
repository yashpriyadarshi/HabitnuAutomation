package com.habitnu.automation.testcases;

import com.habitnu.automation.base.BaseTest;
import com.habitnu.automation.pages.PurchasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {

    @Test
    public void testPurchaseFlight() {
        WebDriver driver = getDriver();
        PurchasePage purchasePage = new PurchasePage(driver);

        Assert.assertTrue(purchasePage.isTotalCostDisplayed(), "'Total Cost' field is not displayed!");

        Assert.assertTrue(purchasePage.isPriceValid(), "Price format is incorrect!");

        purchasePage.clickPurchaseButton();
    }

    @Test(dependsOnMethods = "testPurchaseFlight")
    public void testPurchaseConfirmation() {
        WebDriver driver = getDriver();
        PurchasePage purchasePage = new PurchasePage(driver);

        Assert.assertTrue(purchasePage.isOnConfirmationPage(), "User is NOT on the Purchase Confirmation page!");

        String purchaseId = purchasePage.getPurchaseId();
        System.out.println("Purchase ID: " + purchaseId);
        Reporter.log("Purchase ID: " + purchaseId);
    }
}
