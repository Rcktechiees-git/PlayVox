package com.playvox.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.time.Duration;

/**
 * WebDriver Factory class for managing WebDriver instances
 * Supports Chrome, Firefox, and Edge browsers with automatic driver management
 */
public class DriverFactory {
    
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final int IMPLICIT_WAIT_TIMEOUT = 10;
    private static final int PAGE_LOAD_TIMEOUT = 30;
    
    /**
     * Initialize WebDriver based on browser type
     * @param browserType Browser type (chrome, firefox, edge)
     * @return WebDriver instance
     */
    public static WebDriver initializeDriver(String browserType) {
        WebDriver driver;
        
        switch (browserType.toLowerCase()) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "firefox":
                driver = createFirefoxDriver();
                break;
            case "edge":
                driver = createEdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser type '" + browserType + "' is not supported");
        }
        
        // Configure driver timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        driver.manage().window().maximize();
        
        driverThreadLocal.set(driver);
        return driver;
    }
    
    /**
     * Get current WebDriver instance
     * @return WebDriver current instance
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
    
    /**
     * Quit WebDriver and clean up
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
    
    /**
     * Create Chrome WebDriver with options
     * @return WebDriver Chrome instance
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        // Add headless mode option if needed for CI/CD
        String headlessMode = System.getProperty("headless", "false");
        if ("true".equalsIgnoreCase(headlessMode)) {
            options.addArguments("--headless");
        }
        
        // Additional security and performance options
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--disable-images");
        options.addArguments("--disable-javascript");
        
        return new ChromeDriver(options);
    }
    
    /**
     * Create Firefox WebDriver with options
     * @return WebDriver Firefox instance
     */
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        
        FirefoxOptions options = new FirefoxOptions();
        
        // Add headless mode option if needed for CI/CD
        String headlessMode = System.getProperty("headless", "false");
        if ("true".equalsIgnoreCase(headlessMode)) {
            options.addArguments("--headless");
        }
        
        return new FirefoxDriver(options);
    }
    
    /**
     * Create Edge WebDriver with options
     * @return WebDriver Edge instance
     */
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        // Add headless mode option if needed for CI/CD
        String headlessMode = System.getProperty("headless", "false");
        if ("true".equalsIgnoreCase(headlessMode)) {
            options.addArguments("--headless");
        }
        
        return new EdgeDriver(options);
    }
}