package com.playvox.automation.utils;

/**
 * Test Data Provider class containing test data for PlayVox integration tests
 * Centralizes test data management for better maintainability
 */
public class TestDataProvider {
    
    // Valid test data
    public static final String VALID_CLIENT_ID = "test_client_12345";
    public static final String VALID_CLIENT_SECRET = "test_secret_abcde";
    public static final String VALID_API_KEY = "api_key_xyz789";
    public static final String VALID_API_URL = "https://api.playvox.com/v1";
    
    // Invalid test data
    public static final String INVALID_CLIENT_ID = "invalid_client";
    public static final String INVALID_CLIENT_SECRET = "invalid_secret";
    public static final String INVALID_API_KEY = "invalid_key";
    public static final String INVALID_API_URL = "invalid_url";
    
    // Empty data
    public static final String EMPTY_STRING = "";
    
    // Expected error messages
    public static final String MANDATORY_FIELDS_ERROR = "Please fill the mandatory fields";
    public static final String EMPTY_FIELD_VALIDATION = "empty field validation";
    public static final String VALIDATION_FAILED_ERROR = "validation failed";
    
    // Expected success messages
    public static final String SUCCESS_MESSAGE = "success message";
    public static final String AUTHORIZATION_SUCCESS = "Authorization successful";
    
    /**
     * Get valid test data set
     * @return ValidTestData object with all valid values
     */
    public static ValidTestData getValidTestData() {
        return new ValidTestData(VALID_CLIENT_ID, VALID_CLIENT_SECRET, VALID_API_KEY, VALID_API_URL);
    }
    
    /**
     * Get invalid test data set
     * @return InvalidTestData object with all invalid values
     */
    public static InvalidTestData getInvalidTestData() {
        return new InvalidTestData(INVALID_CLIENT_ID, INVALID_CLIENT_SECRET, INVALID_API_KEY, INVALID_API_URL);
    }
    
    /**
     * Inner class for valid test data
     */
    public static class ValidTestData {
        private final String clientId;
        private final String clientSecret;
        private final String apiKey;
        private final String apiUrl;
        
        public ValidTestData(String clientId, String clientSecret, String apiKey, String apiUrl) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.apiKey = apiKey;
            this.apiUrl = apiUrl;
        }
        
        public String getClientId() { return clientId; }
        public String getClientSecret() { return clientSecret; }
        public String getApiKey() { return apiKey; }
        public String getApiUrl() { return apiUrl; }
    }
    
    /**
     * Inner class for invalid test data
     */
    public static class InvalidTestData {
        private final String clientId;
        private final String clientSecret;
        private final String apiKey;
        private final String apiUrl;
        
        public InvalidTestData(String clientId, String clientSecret, String apiKey, String apiUrl) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.apiKey = apiKey;
            this.apiUrl = apiUrl;
        }
        
        public String getClientId() { return clientId; }
        public String getClientSecret() { return clientSecret; }
        public String getApiKey() { return apiKey; }
        public String getApiUrl() { return apiUrl; }
    }
}