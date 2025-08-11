package com.playvox.automation.tests;

import com.playvox.automation.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case SC003: User partially fills the form, leaving one or more mandatory fields empty
 * 
 * Test Scenario: The user leaves one or more required fields empty and clicks authorize access.
 * 
 * Expected Result: The field should show empty field validation.
 */
public class SC003_PartialFormCompletionTest extends BaseTest {
    
    @Test(description = "TC003-A - Verify validation when Client Secret is missing")
    public void testMissingClientSecret() {
        logTestStep("Starting TC003-A - Missing Client Secret test");
        
        // Step 1: Clear all fields to ensure clean state
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill most fields but leave Client Secret empty
        logTestStep("Step 2: Fill Client ID, API Key, and API URL but leave Client Secret empty");
        integrationPage.enterClientId(TestDataProvider.VALID_CLIENT_ID);
        integrationPage.enterApiKey(TestDataProvider.VALID_API_KEY);
        integrationPage.enterApiUrl(TestDataProvider.VALID_API_URL);
        // Intentionally not filling Client Secret
        
        // Step 3: Click "Authorize Access" button
        logTestStep("Step 3: Click 'Authorize Access' button with Client Secret missing");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify empty field validation is displayed
        logTestStep("Step 4: Verify empty field validation error is displayed for missing Client Secret");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Empty field validation error should be displayed when Client Secret is missing");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("empty") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("validation"),
            "Error message should indicate empty field validation. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC003-A completed successfully - Empty field validation displayed for missing Client Secret");
    }
    
    @Test(description = "TC003-B - Verify validation when API Key is missing")
    public void testMissingApiKey() {
        logTestStep("Starting TC003-B - Missing API Key test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill most fields but leave API Key empty
        logTestStep("Step 2: Fill Client ID, Client Secret, and API URL but leave API Key empty");
        integrationPage.enterClientId(TestDataProvider.VALID_CLIENT_ID);
        integrationPage.enterClientSecret(TestDataProvider.VALID_CLIENT_SECRET);
        integrationPage.enterApiUrl(TestDataProvider.VALID_API_URL);
        // Intentionally not filling API Key
        
        // Step 3: Click "Authorize Access" button
        logTestStep("Step 3: Click 'Authorize Access' button with API Key missing");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify empty field validation is displayed
        logTestStep("Step 4: Verify empty field validation error is displayed for missing API Key");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Empty field validation error should be displayed when API Key is missing");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("empty") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("validation"),
            "Error message should indicate empty field validation. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC003-B completed successfully - Empty field validation displayed for missing API Key");
    }
    
    @Test(description = "TC003-C - Verify validation when multiple fields are missing")
    public void testMultipleFieldsMissing() {
        logTestStep("Starting TC003-C - Multiple fields missing test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill only Client ID, leave multiple other fields empty
        logTestStep("Step 2: Fill only Client ID, leave Client Secret, API Key, and API URL empty");
        integrationPage.enterClientId(TestDataProvider.VALID_CLIENT_ID);
        // Intentionally not filling Client Secret, API Key, and API URL
        
        // Step 3: Click "Authorize Access" button
        logTestStep("Step 3: Click 'Authorize Access' button with multiple fields missing");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify empty field validation is displayed
        logTestStep("Step 4: Verify empty field validation error is displayed for missing multiple fields");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Empty field validation error should be displayed when multiple fields are missing");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("empty") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("validation"),
            "Error message should indicate empty field validation. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC003-C completed successfully - Empty field validation displayed for multiple missing fields");
    }
    
    @Test(description = "TC003-D - Verify validation when API URL is missing")
    public void testMissingApiUrl() {
        logTestStep("Starting TC003-D - Missing API URL test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill most fields but leave API URL empty
        logTestStep("Step 2: Fill Client ID, Client Secret, and API Key but leave API URL empty");
        integrationPage.enterClientId(TestDataProvider.VALID_CLIENT_ID);
        integrationPage.enterClientSecret(TestDataProvider.VALID_CLIENT_SECRET);
        integrationPage.enterApiKey(TestDataProvider.VALID_API_KEY);
        // Intentionally not filling API URL
        
        // Step 3: Click "Authorize Access" button
        logTestStep("Step 3: Click 'Authorize Access' button with API URL missing");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify empty field validation is displayed
        logTestStep("Step 4: Verify empty field validation error is displayed for missing API URL");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Empty field validation error should be displayed when API URL is missing");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("empty") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("validation"),
            "Error message should indicate empty field validation. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC003-D completed successfully - Empty field validation displayed for missing API URL");
    }
}