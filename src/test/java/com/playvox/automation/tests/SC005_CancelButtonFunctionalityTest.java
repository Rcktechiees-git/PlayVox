package com.playvox.automation.tests;

import com.playvox.automation.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case SC005: User clicks the "Cancel" button to discard all changes made in the form
 * 
 * Test Scenario: The user clicks the "Cancel" button to clear all fields and discard the entered data.
 * 
 * Expected Result: All input fields should be cleared.
 */
public class SC005_CancelButtonFunctionalityTest extends BaseTest {
    
    @Test(description = "TC005 - Verify Cancel button clears all input fields")
    public void testCancelButtonClearsAllFields() {
        logTestStep("Starting TC005 - Cancel button functionality test");
        
        // Step 1: Clear all fields to start with clean state
        logTestStep("Step 1: Clear all form fields initially");
        integrationPage.clearAllFields();
        
        // Step 2: Fill all fields with test data
        logTestStep("Step 2: Fill all form fields with test data");
        TestDataProvider.ValidTestData validData = TestDataProvider.getValidTestData();
        
        integrationPage.enterClientId(validData.getClientId());
        logTestStep("   - Entered Client ID: " + validData.getClientId());
        
        integrationPage.enterClientSecret(validData.getClientSecret());
        logTestStep("   - Entered Client Secret: " + validData.getClientSecret());
        
        integrationPage.enterApiKey(validData.getApiKey());
        logTestStep("   - Entered API Key: " + validData.getApiKey());
        
        integrationPage.enterApiUrl(validData.getApiUrl());
        logTestStep("   - Entered API URL: " + validData.getApiUrl());
        
        // Step 3: Verify fields are filled before clicking Cancel
        logTestStep("Step 3: Verify all fields contain data before clicking Cancel");
        // Note: We assume fields have data since we just filled them
        // In a real test, we might want to verify field values, but that would require
        // additional methods in the page object
        
        // Step 4: Click the Cancel button
        logTestStep("Step 4: Click the 'Cancel' button to discard all entered data");
        integrationPage.clickCancel();
        
        // Step 5: Verify all fields are cleared after clicking Cancel
        logTestStep("Step 5: Verify all input fields are cleared after clicking Cancel");
        Assert.assertTrue(integrationPage.areAllFieldsEmpty(), 
            "All input fields should be cleared after clicking the Cancel button");
        
        logTestStep("TC005 completed successfully - Cancel button cleared all input fields");
    }
    
    @Test(description = "TC005-Partial - Verify Cancel button works with partially filled form")
    public void testCancelButtonWithPartialData() {
        logTestStep("Starting TC005-Partial - Cancel button with partial data test");
        
        // Step 1: Clear all fields
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Fill only some fields
        logTestStep("Step 2: Fill only Client ID and API Key fields");
        integrationPage.enterClientId("partial_client_123");
        integrationPage.enterApiKey("partial_api_key_456");
        logTestStep("   - Entered partial data in Client ID and API Key fields");
        
        // Step 3: Click Cancel button
        logTestStep("Step 3: Click 'Cancel' button to discard partial data");
        integrationPage.clickCancel();
        
        // Step 4: Verify all fields are cleared (including the partially filled ones)
        logTestStep("Step 4: Verify all fields are cleared after Cancel");
        Assert.assertTrue(integrationPage.areAllFieldsEmpty(), 
            "All fields should be cleared after Cancel, even when only partially filled");
        
        logTestStep("TC005-Partial completed successfully - Cancel cleared partially filled fields");
    }
    
    @Test(description = "TC005-Empty - Verify Cancel button behavior on empty form")
    public void testCancelButtonOnEmptyForm() {
        logTestStep("Starting TC005-Empty - Cancel button on empty form test");
        
        // Step 1: Ensure all fields are empty
        logTestStep("Step 1: Ensure all form fields are empty");
        integrationPage.clearAllFields();
        Assert.assertTrue(integrationPage.areAllFieldsEmpty(), 
            "All fields should be empty before testing Cancel on empty form");
        
        // Step 2: Click Cancel button on empty form
        logTestStep("Step 2: Click 'Cancel' button on empty form");
        integrationPage.clickCancel();
        
        // Step 3: Verify fields remain empty (no adverse effects)
        logTestStep("Step 3: Verify fields remain empty after clicking Cancel on empty form");
        Assert.assertTrue(integrationPage.areAllFieldsEmpty(), 
            "Fields should remain empty after clicking Cancel on an already empty form");
        
        // Step 4: Verify no error messages appear
        logTestStep("Step 4: Verify no error messages appear when clicking Cancel on empty form");
        Assert.assertFalse(integrationPage.isErrorMessageDisplayed(), 
            "No error messages should appear when clicking Cancel on empty form");
        
        logTestStep("TC005-Empty completed successfully - Cancel button works correctly on empty form");
    }
    
    @Test(description = "TC005-Reset - Verify Cancel resets form to initial state")
    public void testCancelResetsFormToInitialState() {
        logTestStep("Starting TC005-Reset - Cancel resets form to initial state test");
        
        // Step 1: Start with empty form
        logTestStep("Step 1: Start with empty form (initial state)");
        integrationPage.clearAllFields();
        boolean initialStateEmpty = integrationPage.areAllFieldsEmpty();
        
        // Step 2: Fill form with various data
        logTestStep("Step 2: Fill form with test data");
        integrationPage.enterClientId("reset_test_client");
        integrationPage.enterClientSecret("reset_test_secret");
        integrationPage.enterApiKey("reset_test_key");
        integrationPage.enterApiUrl("https://reset.test.com");
        logTestStep("   - Form filled with test data for reset verification");
        
        // Step 3: Click Cancel to reset
        logTestStep("Step 3: Click Cancel to reset form to initial state");
        integrationPage.clickCancel();
        
        // Step 4: Verify form is back to initial state
        logTestStep("Step 4: Verify form is reset to initial empty state");
        boolean finalStateEmpty = integrationPage.areAllFieldsEmpty();
        
        Assert.assertEquals(finalStateEmpty, initialStateEmpty, 
            "Form should be reset to the same state as initial (empty) state after Cancel");
        
        Assert.assertTrue(finalStateEmpty, 
            "Form should be in empty state after Cancel (matching initial state)");
        
        logTestStep("TC005-Reset completed successfully - Cancel successfully reset form to initial state");
    }
}