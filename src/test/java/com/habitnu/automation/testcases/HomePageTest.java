package com.habitnu.automation.testcases;

import com.habitnu.automation.base.BaseTest;
import com.habitnu.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageTitle() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.verifyTitle(), "Home page title is incorrect!");
    }

    @Test
    public void verifyVacationLinkOpensNewTab() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickVacationLink();
        Assert.assertTrue(driver.getCurrentUrl().contains("vacation"), "Vacation link did not open correctly!");
        driver.navigate().back();
    }
}
