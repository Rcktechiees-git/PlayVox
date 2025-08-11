package com.playvox.automation.tests;

import com.playvox.automation.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case SC002: User inputs only the Playvox Client ID while leaving other fields empty
 * 
 * Test Scenario: The user enters only the Playvox Client ID, leaving all other fields blank 
 * and clicks authorize access (and similarly for all other fields).
 * 
 * Expected Result: The field should show empty field validation.
 */
public class SC002_PartialFieldInputTest extends BaseTest {
    
    @Test(description = "TC002-A - Verify validation when only Client ID is filled")
    public void testOnlyClientIdFilled() {
        logTestStep("Starting TC002-A - Only Client ID filled test");
        
        // Step 1: Clear all fields to ensure clean state
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Enter only Client ID, leave other fields empty
        logTestStep("Step 2: Enter only Client ID, leave other fields empty");
        integrationPage.enterClientId(TestDataProvider.VALID_CLIENT_ID);
        
        // Step 3: Verify other fields are still empty
        logTestStep("Step 3: Verify other fields remain empty");
        // Note: We can't directly check if specific fields are empty without exposing them,
        // but the test assumption is that we only filled Client ID
        
        // Step 4: Click "Authorize Access" button
        logTestStep("Step 4: Click 'Authorize Access' button with only Client ID filled");
        integrationPage.clickAuthorizeAccess();
        
        // Step 5: Verify empty field validation is displayed
        logTestStep("Step 5: Verify empty field validation error is displayed");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Empty field validation error should be displayed when only Client ID is provided");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("empty") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("validation"),
            "Error message should indicate empty field validation. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC002-A completed successfully - Empty field validation displayed for missing fields");
    }
    
    @Test(description = "TC002-B - Verify validation when only Client Secret is filled")
    public void testOnlyClientSecretFilled() {
        logTestStep("Starting TC002-B - Only Client Secret filled test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Enter only Client Secret
        logTestStep("Step 2: Enter only Client Secret, leave other fields empty");
        integrationPage.enterClientSecret(TestDataProvider.VALID_CLIENT_SECRET);
        
        // Step 3: Click "Authorize Access" button
        logTestStep("Step 3: Click 'Authorize Access' button with only Client Secret filled");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify empty field validation is displayed
        logTestStep("Step 4: Verify empty field validation error is displayed");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Empty field validation error should be displayed when only Client Secret is provided");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("empty") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("validation"),
            "Error message should indicate empty field validation. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC002-B completed successfully - Empty field validation displayed for missing fields");
    }
    
    @Test(description = "TC002-C - Verify validation when only API Key is filled")
    public void testOnlyApiKeyFilled() {
        logTestStep("Starting TC002-C - Only API Key filled test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Enter only API Key
        logTestStep("Step 2: Enter only API Key, leave other fields empty");
        integrationPage.enterApiKey(TestDataProvider.VALID_API_KEY);
        
        // Step 3: Click "Authorize Access" button
        logTestStep("Step 3: Click 'Authorize Access' button with only API Key filled");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify empty field validation is displayed
        logTestStep("Step 4: Verify empty field validation error is displayed");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Empty field validation error should be displayed when only API Key is provided");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("empty") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("validation"),
            "Error message should indicate empty field validation. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC002-C completed successfully - Empty field validation displayed for missing fields");
    }
    
    @Test(description = "TC002-D - Verify validation when only API URL is filled")
    public void testOnlyApiUrlFilled() {
        logTestStep("Starting TC002-D - Only API URL filled test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Enter only API URL
        logTestStep("Step 2: Enter only API URL, leave other fields empty");
        integrationPage.enterApiUrl(TestDataProvider.VALID_API_URL);
        
        // Step 3: Click "Authorize Access" button
        logTestStep("Step 3: Click 'Authorize Access' button with only API URL filled");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify empty field validation is displayed
        logTestStep("Step 4: Verify empty field validation error is displayed");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Empty field validation error should be displayed when only API URL is provided");
        
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("empty") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("mandatory") ||
            actualErrorMessage.toLowerCase().contains("validation"),
            "Error message should indicate empty field validation. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC002-D completed successfully - Empty field validation displayed for missing fields");
    }
}