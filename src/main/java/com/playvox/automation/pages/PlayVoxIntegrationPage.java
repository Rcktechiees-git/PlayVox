package com.playvox.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import java.util.List;

/**
 * Page Object Model for PlayVox Integration Settings Page
 * Contains all web elements and methods for interacting with the integration page
 */
public class PlayVoxIntegrationPage extends BasePage {
    
    // Page URL - based on the provided URL pattern
    private static final String PAGE_URL = "https://rcktechiees-874988466824422142.myfreshworks.com/crm/sales/settings/integrations/third-party-applications/view/all";
    
    // Form input fields - using common locator patterns for integration forms
    @FindBy(id = "playvox_client_id")
    private WebElement clientIdField;
    
    @FindBy(id = "playvox_client_secret")
    private WebElement clientSecretField;
    
    @FindBy(id = "playvox_api_key")
    private WebElement apiKeyField;
    
    @FindBy(id = "playvox_api_url")
    private WebElement apiUrlField;
    
    @FindBy(name = "client_id")
    private WebElement clientIdFieldByName;
    
    @FindBy(name = "client_secret")
    private WebElement clientSecretFieldByName;
    
    @FindBy(name = "api_key")
    private WebElement apiKeyFieldByName;
    
    @FindBy(name = "api_url")
    private WebElement apiUrlFieldByName;
    
    // Alternative locators using CSS selectors
    @FindBy(css = "input[placeholder*='Client ID']")
    private WebElement clientIdFieldByPlaceholder;
    
    @FindBy(css = "input[placeholder*='Client Secret']")
    private WebElement clientSecretFieldByPlaceholder;
    
    @FindBy(css = "input[placeholder*='API Key']")
    private WebElement apiKeyFieldByPlaceholder;
    
    @FindBy(css = "input[placeholder*='API URL']")
    private WebElement apiUrlFieldByPlaceholder;
    
    // Action buttons
    @FindBy(xpath = "//button[contains(text(), 'Authorize Access') or contains(@value, 'Authorize') or contains(@class, 'authorize')]")
    private WebElement authorizeAccessButton;
    
    @FindBy(xpath = "//button[contains(text(), 'Save') or contains(@value, 'Save') or contains(@class, 'save')]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//button[contains(text(), 'Cancel') or contains(@value, 'Cancel') or contains(@class, 'cancel')]")
    private WebElement cancelButton;
    
    // Alternative button locators
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;
    
    @FindBy(css = "input[type='submit']")
    private WebElement inputSubmitButton;
    
    // Error and success message elements
    @FindBy(xpath = "//div[contains(@class, 'error') or contains(@class, 'alert-danger')]")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//div[contains(@class, 'success') or contains(@class, 'alert-success')]")
    private WebElement successMessage;
    
    @FindBy(xpath = "//span[contains(@class, 'error') or contains(@class, 'invalid')]")
    private WebElement fieldValidationError;
    
    // Generic validation messages
    @FindBy(xpath = "//div[contains(text(), 'mandatory') or contains(text(), 'required') or contains(text(), 'Please fill')]")
    private WebElement mandatoryFieldError;
    
    @FindBy(xpath = "//div[contains(text(), 'validation') or contains(text(), 'invalid')]")
    private WebElement validationError;
    
    // Form container
    @FindBy(css = "form, .form, .integration-form")
    private WebElement formContainer;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public PlayVoxIntegrationPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to the PlayVox integration page
     */
    public void navigateToPage() {
        driver.get(PAGE_URL);
        waitForPageToLoad();
    }
    
    /**
     * Enter Client ID in the form
     * @param clientId Client ID value to enter
     */
    public void enterClientId(String clientId) {
        WebElement field = getAvailableElement(clientIdField, clientIdFieldByName, clientIdFieldByPlaceholder);
        if (field != null) {
            clearAndEnterText(field, clientId);
        } else {
            throw new RuntimeException("Client ID field not found on the page");
        }
    }
    
    /**
     * Enter Client Secret in the form
     * @param clientSecret Client Secret value to enter
     */
    public void enterClientSecret(String clientSecret) {
        WebElement field = getAvailableElement(clientSecretField, clientSecretFieldByName, clientSecretFieldByPlaceholder);
        if (field != null) {
            clearAndEnterText(field, clientSecret);
        } else {
            throw new RuntimeException("Client Secret field not found on the page");
        }
    }
    
    /**
     * Enter API Key in the form
     * @param apiKey API Key value to enter
     */
    public void enterApiKey(String apiKey) {
        WebElement field = getAvailableElement(apiKeyField, apiKeyFieldByName, apiKeyFieldByPlaceholder);
        if (field != null) {
            clearAndEnterText(field, apiKey);
        } else {
            throw new RuntimeException("API Key field not found on the page");
        }
    }
    
    /**
     * Enter API URL in the form
     * @param apiUrl API URL value to enter
     */
    public void enterApiUrl(String apiUrl) {
        WebElement field = getAvailableElement(apiUrlField, apiUrlFieldByName, apiUrlFieldByPlaceholder);
        if (field != null) {
            clearAndEnterText(field, apiUrl);
        } else {
            throw new RuntimeException("API URL field not found on the page");
        }
    }
    
    /**
     * Click the Authorize Access button
     */
    public void clickAuthorizeAccess() {
        WebElement button = getAvailableElement(authorizeAccessButton, submitButton, inputSubmitButton);
        if (button != null) {
            clickElement(button);
        } else {
            throw new RuntimeException("Authorize Access button not found on the page");
        }
    }
    
    /**
     * Click the Save button
     */
    public void clickSave() {
        WebElement button = getAvailableElement(saveButton);
        if (button != null) {
            clickElement(button);
        } else {
            throw new RuntimeException("Save button not found on the page");
        }
    }
    
    /**
     * Click the Cancel button
     */
    public void clickCancel() {
        WebElement button = getAvailableElement(cancelButton);
        if (button != null) {
            clickElement(button);
        } else {
            throw new RuntimeException("Cancel button not found on the page");
        }
    }
    
    /**
     * Get error message text
     * @return String error message
     */
    public String getErrorMessage() {
        WebElement error = getAvailableElement(errorMessage, mandatoryFieldError, validationError, fieldValidationError);
        return error != null ? getElementText(error) : "";
    }
    
    /**
     * Get success message text
     * @return String success message
     */
    public String getSuccessMessage() {
        return isElementDisplayed(successMessage) ? getElementText(successMessage) : "";
    }
    
    /**
     * Check if error message is displayed
     * @return boolean true if error message is visible
     */
    public boolean isErrorMessageDisplayed() {
        WebElement error = getAvailableElement(errorMessage, mandatoryFieldError, validationError, fieldValidationError);
        return error != null && isElementDisplayed(error);
    }
    
    /**
     * Check if success message is displayed
     * @return boolean true if success message is visible
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }
    
    /**
     * Clear all form fields
     */
    public void clearAllFields() {
        clearField(getAvailableElement(clientIdField, clientIdFieldByName, clientIdFieldByPlaceholder));
        clearField(getAvailableElement(clientSecretField, clientSecretFieldByName, clientSecretFieldByPlaceholder));
        clearField(getAvailableElement(apiKeyField, apiKeyFieldByName, apiKeyFieldByPlaceholder));
        clearField(getAvailableElement(apiUrlField, apiUrlFieldByName, apiUrlFieldByPlaceholder));
    }
    
    /**
     * Check if all fields are empty
     * @return boolean true if all fields are empty
     */
    public boolean areAllFieldsEmpty() {
        return isFieldEmpty(getAvailableElement(clientIdField, clientIdFieldByName, clientIdFieldByPlaceholder)) &&
               isFieldEmpty(getAvailableElement(clientSecretField, clientSecretFieldByName, clientSecretFieldByPlaceholder)) &&
               isFieldEmpty(getAvailableElement(apiKeyField, apiKeyFieldByName, apiKeyFieldByPlaceholder)) &&
               isFieldEmpty(getAvailableElement(apiUrlField, apiUrlFieldByName, apiUrlFieldByPlaceholder));
    }
    
    /**
     * Fill the complete form with valid data
     * @param clientId Client ID value
     * @param clientSecret Client Secret value
     * @param apiKey API Key value
     * @param apiUrl API URL value
     */
    public void fillCompleteForm(String clientId, String clientSecret, String apiKey, String apiUrl) {
        enterClientId(clientId);
        enterClientSecret(clientSecret);
        enterApiKey(apiKey);
        enterApiUrl(apiUrl);
    }
    
    /**
     * Check if the current page is the App Settings page (for redirect validation)
     * @return boolean true if on App Settings page
     */
    public boolean isOnAppSettingsPage() {
        String currentUrl = getCurrentUrl();
        String pageTitle = getPageTitle();
        return currentUrl.contains("settings") || pageTitle.toLowerCase().contains("app settings");
    }
    
    /**
     * Helper method to get the first available element from multiple options
     * @param elements Variable number of WebElement options
     * @return WebElement first available element or null
     */
    private WebElement getAvailableElement(WebElement... elements) {
        for (WebElement element : elements) {
            if (element != null && isElementDisplayed(element)) {
                return element;
            }
        }
        return null;
    }
    
    /**
     * Helper method to clear a field if it exists
     * @param field WebElement field to clear
     */
    private void clearField(WebElement field) {
        if (field != null && isElementDisplayed(field)) {
            field.clear();
        }
    }
    
    /**
     * Helper method to check if a field is empty
     * @param field WebElement field to check
     * @return boolean true if field is empty
     */
    private boolean isFieldEmpty(WebElement field) {
        if (field != null && isElementDisplayed(field)) {
            String value = field.getAttribute("value");
            return value == null || value.trim().isEmpty();
        }
        return true;
    }
}