package com.mckesson.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InitializeBrowser {

    public WebDriver initializeBrowserSettings(String browser, WebDriver driver, Boolean headless){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ecbkpju\\IdeaProjects\\McKesson_Demo\\src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        switch (browser) {
            case "FireFox":
                driver = new FirefoxDriver();
                break;
            case "InternetExplorer":
                driver = new InternetExplorerDriver();
                break;
            default:
                if (headless) {
                    driver = new ChromeDriver(options);
                } else {
                    driver = new ChromeDriver();
                }
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }
}
