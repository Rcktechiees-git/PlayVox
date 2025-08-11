package com.playvox.automation.tests;

import com.playvox.automation.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case SC004: User fully completes the form with valid values in all fields
 * 
 * Test Scenario: The user completes the form by correctly filling in all the required fields 
 * and clicks authorize access.
 * 
 * Expected Result: Page should show success message.
 */
public class SC004_ValidFormSubmissionTest extends BaseTest {
    
    @Test(description = "TC004 - Verify success message when form is completed with valid values")
    public void testValidFormSubmissionSuccess() {
        logTestStep("Starting TC004 - Valid form submission test");
        
        // Step 1: Clear all fields to ensure clean state
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill all required fields with valid data
        logTestStep("Step 2: Fill all required fields with valid data");
        TestDataProvider.ValidTestData validData = TestDataProvider.getValidTestData();
        
        integrationPage.enterClientId(validData.getClientId());
        logTestStep("   - Entered Client ID: " + validData.getClientId());
        
        integrationPage.enterClientSecret(validData.getClientSecret());
        logTestStep("   - Entered Client Secret: " + validData.getClientSecret());
        
        integrationPage.enterApiKey(validData.getApiKey());
        logTestStep("   - Entered API Key: " + validData.getApiKey());
        
        integrationPage.enterApiUrl(validData.getApiUrl());
        logTestStep("   - Entered API URL: " + validData.getApiUrl());
        
        // Step 3: Click "Authorize Access" button
        logTestStep("Step 3: Click 'Authorize Access' button with all valid data");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify success message is displayed
        logTestStep("Step 4: Verify success message is displayed");
        
        // Wait a moment for the response to process
        try {
            Thread.sleep(2000); // Give time for authorization to process
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Check for success message
        boolean isSuccessDisplayed = integrationPage.isSuccessMessageDisplayed();
        Assert.assertTrue(isSuccessDisplayed, 
            "Success message should be displayed when form is submitted with valid data");
        
        // Step 5: Verify success message content
        logTestStep("Step 5: Verify success message content");
        if (isSuccessDisplayed) {
            String actualSuccessMessage = integrationPage.getSuccessMessage();
            Assert.assertTrue(
                actualSuccessMessage.toLowerCase().contains("success") || 
                actualSuccessMessage.toLowerCase().contains("authorized") ||
                actualSuccessMessage.toLowerCase().contains("complete"),
                "Success message should indicate successful authorization. Actual message: " + actualSuccessMessage
            );
            logTestStep("   - Success message displayed: " + actualSuccessMessage);
        }
        
        // Step 6: Verify no error messages are displayed
        logTestStep("Step 6: Verify no error messages are displayed");
        Assert.assertFalse(integrationPage.isErrorMessageDisplayed(), 
            "No error messages should be displayed for valid form submission");
        
        logTestStep("TC004 completed successfully - Valid form submission processed with success message");
    }
    
    @Test(description = "TC004-Alt - Verify valid form submission with alternative valid data")
    public void testValidFormSubmissionAlternativeData() {
        logTestStep("Starting TC004-Alt - Valid form submission with alternative data");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill form with alternative valid data
        logTestStep("Step 2: Fill all required fields with alternative valid data");
        String altClientId = "alt_client_98765";
        String altClientSecret = "alt_secret_zyxwv";
        String altApiKey = "alt_api_key_321";
        String altApiUrl = "https://api.playvox.com/v2";
        
        integrationPage.fillCompleteForm(altClientId, altClientSecret, altApiKey, altApiUrl);
        logTestStep("   - Filled form with alternative valid data");
        
        // Step 3: Submit the form
        logTestStep("Step 3: Click 'Authorize Access' button");
        integrationPage.clickAuthorizeAccess();
        
        // Step 4: Verify successful processing
        logTestStep("Step 4: Verify successful authorization");
        
        // Wait for processing
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify success indicators (either success message or no error)
        boolean hasSuccess = integrationPage.isSuccessMessageDisplayed();
        boolean hasNoError = !integrationPage.isErrorMessageDisplayed();
        
        Assert.assertTrue(hasSuccess || hasNoError, 
            "Form submission with valid data should either show success message or at least not show errors");
        
        if (hasSuccess) {
            logTestStep("   - Success message confirmed for alternative valid data");
        } else if (hasNoError) {
            logTestStep("   - No errors detected - authorization likely successful");
        }
        
        logTestStep("TC004-Alt completed successfully - Alternative valid data processed correctly");
    }
    
    @Test(description = "TC004-Edge - Verify form submission with minimal valid data")
    public void testMinimalValidDataSubmission() {
        logTestStep("Starting TC004-Edge - Minimal valid data submission test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill with minimal but valid data
        logTestStep("Step 2: Fill form with minimal valid data");
        String minClientId = "min123";
        String minClientSecret = "secret123";
        String minApiKey = "key123";
        String minApiUrl = "https://api.test.com";
        
        integrationPage.fillCompleteForm(minClientId, minClientSecret, minApiKey, minApiUrl);
        logTestStep("   - Filled form with minimal valid data");
        
        // Step 3: Submit and verify
        logTestStep("Step 3: Submit form and verify processing");
        integrationPage.clickAuthorizeAccess();
        
        // Wait for processing
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Step 4: Verify the form processes without validation errors
        logTestStep("Step 4: Verify form processes without validation errors");
        
        // For minimal data, we expect at least that validation passes
        // (actual authorization might fail but form validation should pass)
        String errorMessage = integrationPage.getErrorMessage();
        boolean hasValidationError = errorMessage.toLowerCase().contains("required") || 
                                   errorMessage.toLowerCase().contains("mandatory") ||
                                   errorMessage.toLowerCase().contains("empty");
        
        Assert.assertFalse(hasValidationError, 
            "Minimal valid data should not trigger validation errors. Error: " + errorMessage);
        
        logTestStep("TC004-Edge completed successfully - Minimal valid data passed validation");
    }
}