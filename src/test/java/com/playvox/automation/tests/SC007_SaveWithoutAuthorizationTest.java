package com.playvox.automation.tests;

import com.playvox.automation.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case SC007: User attempts to save the form without completing authorization or with invalid/incomplete data
 * 
 * Test Scenario: The user clicks the "Save" button without completing authorization or with 
 * invalid or incomplete inputs.
 * 
 * Expected Result: The page should show validation failed.
 */
public class SC007_SaveWithoutAuthorizationTest extends BaseTest {
    
    @Test(description = "TC007-A - Verify validation failed when saving without authorization")
    public void testSaveWithoutAuthorization() {
        logTestStep("Starting TC007-A - Save without authorization test");
        
        // Step 1: Clear all fields to start fresh
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill form with valid data but do NOT authorize
        logTestStep("Step 2: Fill form with valid data but skip authorization step");
        TestDataProvider.ValidTestData validData = TestDataProvider.getValidTestData();
        
        integrationPage.fillCompleteForm(
            validData.getClientId(),
            validData.getClientSecret(),
            validData.getApiKey(),
            validData.getApiUrl()
        );
        logTestStep("   - Form filled with valid data but authorization step skipped");
        
        // Step 3: Directly click Save without authorizing first
        logTestStep("Step 3: Click 'Save' button without completing authorization first");
        integrationPage.clickSave();
        
        // Step 4: Verify validation failed message is displayed
        logTestStep("Step 4: Verify validation failed message is displayed");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Validation failed message should be displayed when saving without authorization");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("validation") || 
            actualErrorMessage.toLowerCase().contains("failed") ||
            actualErrorMessage.toLowerCase().contains("authorize") ||
            actualErrorMessage.toLowerCase().contains("not authorized"),
            "Error message should indicate validation failed or authorization required. Actual: " + actualErrorMessage
        );
        
        logTestStep("TC007-A completed successfully - Validation failed message displayed for save without authorization");
    }
    
    @Test(description = "TC007-B - Verify validation failed when saving with invalid data")
    public void testSaveWithInvalidData() {
        logTestStep("Starting TC007-B - Save with invalid data test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill form with invalid data
        logTestStep("Step 2: Fill form with invalid data");
        TestDataProvider.InvalidTestData invalidData = TestDataProvider.getInvalidTestData();
        
        integrationPage.fillCompleteForm(
            invalidData.getClientId(),
            invalidData.getClientSecret(),
            invalidData.getApiKey(),
            invalidData.getApiUrl()
        );
        logTestStep("   - Form filled with invalid data");
        
        // Step 3: Attempt to authorize with invalid data (this should fail)
        logTestStep("Step 3: Attempt authorization with invalid data");
        integrationPage.clickAuthorizeAccess();
        
        // Wait for authorization attempt to process
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Step 4: Click Save after failed authorization
        logTestStep("Step 4: Click 'Save' button after failed authorization with invalid data");
        integrationPage.clickSave();
        
        // Step 5: Verify validation failed message
        logTestStep("Step 5: Verify validation failed message is displayed");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Validation failed message should be displayed when saving with invalid data");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("validation") || 
            actualErrorMessage.toLowerCase().contains("failed") ||
            actualErrorMessage.toLowerCase().contains("invalid") ||
            actualErrorMessage.toLowerCase().contains("error"),
            "Error message should indicate validation failed with invalid data. Actual: " + actualErrorMessage
        );
        
        logTestStep("TC007-B completed successfully - Validation failed message displayed for invalid data");
    }
    
    @Test(description = "TC007-C - Verify validation failed when saving with incomplete data")
    public void testSaveWithIncompleteData() {
        logTestStep("Starting TC007-C - Save with incomplete data test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill form with incomplete data (missing some required fields)
        logTestStep("Step 2: Fill form with incomplete data");
        integrationPage.enterClientId("incomplete_client_123");
        integrationPage.enterApiKey("incomplete_key_456");
        // Intentionally leave Client Secret and API URL empty
        logTestStep("   - Form filled with incomplete data (missing Client Secret and API URL)");
        
        // Step 3: Attempt to authorize with incomplete data
        logTestStep("Step 3: Attempt authorization with incomplete data");
        integrationPage.clickAuthorizeAccess();
        
        // Wait for authorization attempt
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Step 4: Click Save after incomplete authorization
        logTestStep("Step 4: Click 'Save' button with incomplete authorization");
        integrationPage.clickSave();
        
        // Step 5: Verify validation failed message
        logTestStep("Step 5: Verify validation failed message for incomplete data");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Validation failed message should be displayed when saving with incomplete data");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("validation") || 
            actualErrorMessage.toLowerCase().contains("failed") ||
            actualErrorMessage.toLowerCase().contains("incomplete") ||
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory"),
            "Error message should indicate validation failed due to incomplete data. Actual: " + actualErrorMessage
        );
        
        logTestStep("TC007-C completed successfully - Validation failed message displayed for incomplete data");
    }
    
    @Test(description = "TC007-D - Verify validation failed when saving empty form")
    public void testSaveWithEmptyForm() {
        logTestStep("Starting TC007-D - Save with empty form test");
        
        // Step 1: Ensure all fields are empty
        logTestStep("Step 1: Ensure all form fields are empty");
        integrationPage.clearAllFields();
        Assert.assertTrue(integrationPage.areAllFieldsEmpty(), 
            "All fields should be empty for this test");
        
        // Step 2: Directly click Save without any data or authorization
        logTestStep("Step 2: Click 'Save' button with completely empty form");
        integrationPage.clickSave();
        
        // Step 3: Verify validation failed message for empty form
        logTestStep("Step 3: Verify validation failed message for empty form");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Validation failed message should be displayed when saving empty form");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("validation") || 
            actualErrorMessage.toLowerCase().contains("failed") ||
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("empty"),
            "Error message should indicate validation failed for empty form. Actual: " + actualErrorMessage
        );
        
        logTestStep("TC007-D completed successfully - Validation failed message displayed for empty form save");
    }
}