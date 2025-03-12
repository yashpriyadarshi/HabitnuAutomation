package com.habitnu.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    private final By pageTitle = By.xpath("//h1");
    private final By vacationLink = By.xpath("//a[contains(text(),'destination')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean verifyTitle() {
        return driver.findElement(pageTitle).getText().equals("Welcome to the Simple Travel Agency!");
    }

    public void clickVacationLink() {
        driver.findElement(vacationLink).click();
    }




}
