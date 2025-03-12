package com.habitnu.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FlightsPage {
    private final WebDriver driver;


    private final By fromPortDropdown = By.name("fromPort");
    private final By toPortDropdown = By.name("toPort");
    private final By findFlights = By.xpath("//input[@type='submit']");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDepartureCity(String departureCity) {
        WebElement fromDropdown = driver.findElement(fromPortDropdown);
        Select selectFrom = new Select(fromDropdown);
        selectFrom.selectByVisibleText(departureCity);
    }

    public void selectDestinationCity(String destinationCity) {
        WebElement toDropdown = driver.findElement(toPortDropdown);
        Select selectTo = new Select(toDropdown);
        selectTo.selectByVisibleText(destinationCity);
    }

    public void clickFindFlights() {
        driver.findElement(findFlights).click();
    }

    public void selectCheapestFlight(){
        String tableRowsXPath = "//table[@class='table']/tbody/tr";
        List<WebElement> rows = driver.findElements(By.xpath(tableRowsXPath));
        double minPrice = Double.MAX_VALUE;
        int minPriceRowIndex = -1;

        for (int i = 1; i <= rows.size(); i++) {
            String priceXPath = "//table[@class='table']/tbody/tr[" + i + "]/td[last()]";
            WebElement priceElement = driver.findElement(By.xpath(priceXPath));
            double price = Double.parseDouble(priceElement.getText().replace("$", "").trim());
            if (price < minPrice) {
                minPrice = price;
                minPriceRowIndex = i;
            }
        }

        if (minPriceRowIndex != -1) {

            String buttonXPath = "//table[@class='table']/tbody/tr[" + minPriceRowIndex + "]/td[1]/input";
            WebElement chooseButton = driver.findElement(By.xpath(buttonXPath));
            chooseButton.click();
        } else {
            throw new RuntimeException("No flights available to choose from.");
        }
    }


}
