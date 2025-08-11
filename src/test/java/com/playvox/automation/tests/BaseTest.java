package com.playvox.automation.tests;

import com.playvox.automation.pages.PlayVoxIntegrationPage;
import com.playvox.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Base Test class containing common setup and teardown methods
 * All test classes should extend this class for consistent test execution
 */
public abstract class BaseTest {
    
    protected WebDriver driver;
    protected PlayVoxIntegrationPage integrationPage;
    
    /**
     * Setup method executed before all tests in the class
     * Initializes WebDriver and page objects
     */
    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) {
        // Initialize WebDriver with specified browser (default: chrome)
        String browserType = (browser != null && !browser.isEmpty()) ? browser : "chrome";
        driver = DriverFactory.initializeDriver(browserType);
        
        // Initialize page objects
        integrationPage = new PlayVoxIntegrationPage(driver);
        
        System.out.println("Test setup completed for browser: " + browserType);
    }
    
    /**
     * Setup method executed before each test method
     * Navigates to the integration page for a fresh start
     */
    @BeforeMethod
    public void setUpMethod() {
        // Navigate to the PlayVox integration page before each test
        integrationPage.navigateToPage();
        System.out.println("Navigated to PlayVox integration page");
    }
    
    /**
     * Cleanup method executed after each test method
     * Can be used for test-specific cleanup if needed
     */
    @AfterMethod
    public void tearDownMethod() {
        // Clear any form data or reset page state if needed
        // This helps ensure test isolation
        try {
            integrationPage.clearAllFields();
        } catch (Exception e) {
            System.out.println("Note: Could not clear fields - page may have navigated away");
        }
    }
    
    /**
     * Cleanup method executed after all tests in the class
     * Quits WebDriver and performs final cleanup
     */
    @AfterClass
    public void tearDown() {
        // Quit WebDriver
        DriverFactory.quitDriver();
        System.out.println("Test teardown completed - WebDriver closed");
    }
    
    /**
     * Helper method to take screenshot for debugging
     * Can be called from test methods when needed
     */
    protected void takeScreenshot(String testName) {
        // Implementation can be added later if screenshot capability is needed
        System.out.println("Screenshot requested for test: " + testName);
    }
    
    /**
     * Helper method to log test step information
     * @param stepDescription Description of the test step
     */
    protected void logTestStep(String stepDescription) {
        System.out.println("Test Step: " + stepDescription);
    }
}