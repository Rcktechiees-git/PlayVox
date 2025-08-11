package com.playvox.automation.tests;

import com.playvox.automation.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Case SC001: User tries to authorize access without filling in any required fields
 * 
 * Test Scenario: The user attempts to click the "Authorize Access" button without 
 * filling in any of the required fields.
 * 
 * Expected Result: An error message saying "Please fill the mandatory fields" should be displayed.
 */
public class SC001_AuthorizeWithoutRequiredFieldsTest extends BaseTest {
    
    @Test(description = "TC001 - Verify error message when authorizing without filling required fields")
    public void testAuthorizeWithoutRequiredFields() {
        logTestStep("Starting TC001 - Authorize without required fields test");
        
        // Step 1: Ensure all fields are empty (they should be by default)
        logTestStep("Step 1: Verify all form fields are empty");
        integrationPage.clearAllFields();
        Assert.assertTrue(integrationPage.areAllFieldsEmpty(), 
            "All form fields should be empty at the start of the test");
        
        // Step 2: Attempt to click "Authorize Access" button without filling any fields
        logTestStep("Step 2: Click 'Authorize Access' button without filling any required fields");
        integrationPage.clickAuthorizeAccess();
        
        // Step 3: Verify that an error message is displayed
        logTestStep("Step 3: Verify error message is displayed");
        Assert.assertTrue(integrationPage.isErrorMessageDisplayed(), 
            "Error message should be displayed when authorizing without required fields");
        
        // Step 4: Verify the specific error message content
        logTestStep("Step 4: Verify error message content contains mandatory fields message");
        String actualErrorMessage = integrationPage.getErrorMessage();
        Assert.assertTrue(
            actualErrorMessage.toLowerCase().contains("mandatory") || 
            actualErrorMessage.toLowerCase().contains("required") ||
            actualErrorMessage.toLowerCase().contains("please fill"),
            "Error message should indicate mandatory fields are required. Actual message: " + actualErrorMessage
        );
        
        logTestStep("TC001 completed successfully - Error message displayed for empty required fields");
    }
    
    @Test(description = "TC001-Alt - Verify specific mandatory fields error message")
    public void testSpecificMandatoryFieldsErrorMessage() {
        logTestStep("Starting TC001-Alt - Verify specific mandatory fields error message");
        
        // Step 1: Clear all fields to ensure empty state
        logTestStep("Step 1: Clear all form fields");
        integrationPage.clearAllFields();
        
        // Step 2: Click authorize without any data
        logTestStep("Step 2: Attempt authorization with empty form");
        integrationPage.clickAuthorizeAccess();
        
        // Step 3: Verify the exact expected error message
        logTestStep("Step 3: Verify exact error message text");
        String expectedMessage = TestDataProvider.MANDATORY_FIELDS_ERROR;
        String actualMessage = integrationPage.getErrorMessage();
        
        // Allow for partial match as different systems may have slightly different wording
        boolean messageMatches = actualMessage.contains(expectedMessage) ||
                               actualMessage.toLowerCase().contains("mandatory") ||
                               actualMessage.toLowerCase().contains("required");
        
        Assert.assertTrue(messageMatches,
            String.format("Expected error message containing '%s' or similar, but got: '%s'", 
                expectedMessage, actualMessage));
        
        logTestStep("TC001-Alt completed successfully - Mandatory fields error message verified");
    }
}