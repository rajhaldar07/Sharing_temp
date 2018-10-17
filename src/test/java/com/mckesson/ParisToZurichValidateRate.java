package com.mckesson;

import com.jayway.restassured.path.json.JsonPath;
import com.mckesson.Actions.*;
import com.mckesson.Utilities.Environment;
import com.mckesson.Utilities.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;


public class ParisToZurichValidateRate {

    private String url, userName, password;
    private String fType, pass, dCity, aCity;
    private String dMonth, dDay, aMonth, aDay;
    private String airline, sClass;
    WebDriver driver;

    @BeforeSuite
    @Parameters({"testDataFile"})
    private void getTestData(String testDataFile) throws IOException {
        Environment.fileName = testDataFile;
        JsonPath jsonPath = new TestData().get(testDataFile);
        this.url = jsonPath.getString("URL");
        this.password = jsonPath.getString("password");
        this.userName = jsonPath.getString("userName");
        this.fType = jsonPath.getString("ParisToZurich.flightType");
        this.pass = jsonPath.getString("ParisToZurich.passengers");
        this.dCity = jsonPath.getString("ParisToZurich.departingFrom");
        this.aCity = jsonPath.getString("ParisToZurich.arrivingIn");
        this.dMonth = jsonPath.getString("ParisToZurich.departingMonth");
        this.dDay = jsonPath.getString("ParisToZurich.departingDay");
        this.aMonth = jsonPath.getString("ParisToZurich.arrivingMonth");
        this.aDay = jsonPath.getString("ParisToZurich.arrivingDay");
        this.airline = jsonPath.getString("ParisToZurich.airlinePreference");
        this.sClass = jsonPath.getString("ParisToZurich.serviceClass");

    }

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void initializeWebDriver(String browser, Boolean headless) {
        driver = new InitializeBrowser().initializeBrowserSettings(browser, driver, headless);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test()
    public void ValidateRate(){
        Assert.assertTrue(new LaunchApplication().launchApp(driver, url));
        Assert.assertTrue(new Login().login(driver, userName, password));
        Assert.assertTrue(new EnterFlightDetails().EnterDetails(driver, fType, pass, dCity, aCity));
        Assert.assertTrue(new EnterFlightDetails().EnterDates(driver, dMonth, dDay, aMonth, aDay));
        Assert.assertTrue(new EnterFlightDetails().FlightPreferencesAndContinue(driver, sClass, airline));
        Assert.assertTrue(new SelectFlights().ValidateDepartureRates(driver));
    }
}
