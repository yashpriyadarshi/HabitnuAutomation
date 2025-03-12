package com.habitnu.automation.testcases;

import com.habitnu.automation.base.BaseTest;
import com.habitnu.automation.pages.FlightsPage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FlightBookingTest extends BaseTest {

    public static String readExcelData(String fileName, int sheetIndex, int row, int cell) {
        String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/" + fileName;
        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            return sheet.getRow(row).getCell(cell).getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    @Test
    public void testFlightSelection() {
        WebDriver driver = getDriver();
        FlightsPage flightsPage = new FlightsPage(driver);

        try {
            String departureCity = readExcelData("testdata.xlsx", 0, 1, 0);
            String destinationCity = readExcelData("testdata.xlsx", 0, 1, 1);

            flightsPage.selectDepartureCity(departureCity);
            flightsPage.selectDestinationCity(destinationCity);
            flightsPage.clickFindFlights();

            Assert.assertEquals(departureCity, "Mexico City", "Departure city mismatch!");
            Assert.assertEquals(destinationCity, "London", "Destination city mismatch!");
        } catch (Exception e) {
            Assert.fail("Test execution failed due to: " + e.getMessage());
        }
    }

    @Test
    public void testSelectCheapestFlight() {
        WebDriver driver = getDriver();
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.selectCheapestFlight();
    }
}
