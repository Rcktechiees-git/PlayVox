# PlayVox Integration Test Automation

This repository contains Selenium-based Java automation test scripts for the PlayVox integration page, implementing comprehensive test cases following the Page Object Model (POM) design pattern.

## 📋 Test Cases Covered

Based on the Excel file `installation_page.xlsx`, the following test scenarios are automated:

| Test Case ID | Description | Expected Result |
|--------------|-------------|-----------------|
| **SC001** | Authorize without filling required fields | Error message: "Please fill the mandatory fields" |
| **SC002** | Fill only one field, leave others empty | Empty field validation error |
| **SC003** | Partially fill form, leave mandatory fields empty | Empty field validation error |
| **SC004** | Complete form with valid values and authorize | Success message displayed |
| **SC005** | Click Cancel button to clear fields | All input fields cleared |
| **SC006** | Click Save after successful authorization | Redirect to App Settings page |
| **SC007** | Save without authorization or with invalid data | Validation failed message |
| **SC008** | Visual inspection of UI alignment | Proper design alignment |

## 🏗️ Project Structure

```
PlayVox/
├── src/
│   ├── main/java/com/playvox/automation/
│   │   ├── pages/
│   │   │   ├── BasePage.java                 # Base page with common methods
│   │   │   └── PlayVoxIntegrationPage.java   # Page object for integration page
│   │   └── utils/
│   │       ├── DriverFactory.java            # WebDriver management
│   │       └── TestDataProvider.java         # Test data management
│   └── test/
│       ├── java/com/playvox/automation/tests/
│       │   ├── BaseTest.java                 # Base test class
│       │   ├── SC001_AuthorizeWithoutRequiredFieldsTest.java
│       │   ├── SC002_PartialFieldInputTest.java
│       │   ├── SC003_PartialFormCompletionTest.java
│       │   ├── SC004_ValidFormSubmissionTest.java
│       │   ├── SC005_CancelButtonFunctionalityTest.java
│       │   ├── SC006_SaveAfterAuthorizationTest.java
│       │   ├── SC007_SaveWithoutAuthorizationTest.java
│       │   └── SC008_UIAlignmentInspectionTest.java
│       └── resources/
│           ├── testng.xml                    # TestNG suite configuration
│           └── config.properties             # Test configuration
├── pom.xml                                   # Maven dependencies
└── installation_page.xlsx                   # Original test case document
```

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser installed

### Dependencies

The project uses the following key dependencies:
- **Selenium WebDriver 4.15.0** - Web automation framework
- **TestNG 7.8.0** - Testing framework
- **WebDriverManager 5.6.2** - Automatic driver management
- **ExtentReports 5.1.1** - Test reporting
- **Apache POI 5.2.4** - Excel file handling

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd PlayVox
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Verify setup:**
   ```bash
   mvn test-compile
   ```

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Suite
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run with Different Browser
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Run in Headless Mode
```bash
mvn test -Headless=true
```

### Run Specific Test Class
```bash
mvn test -Dtest=SC004_ValidFormSubmissionTest
```

### Run Smoke Tests Only
```bash
mvn test -Dtest=SC001_AuthorizeWithoutRequiredFieldsTest#testAuthorizeWithoutRequiredFields,SC004_ValidFormSubmissionTest#testValidFormSubmissionSuccess
```

## 🏛️ Architecture & Design Patterns

### Page Object Model (POM)
- **BasePage.java**: Contains common methods and WebDriver utilities
- **PlayVoxIntegrationPage.java**: Specific page object with locators and actions for the integration page

### Test Design Features
- ✅ **Explicit Waits**: Using WebDriverWait instead of Thread.sleep
- ✅ **Meaningful Comments**: Comprehensive documentation in all classes
- ✅ **Assertions**: TestNG assertions for result verification
- ✅ **Readable Locators**: Multiple locator strategies (ID, Name, CSS, XPath)
- ✅ **Negative Test Handling**: Graceful handling of error scenarios
- ✅ **Clean Code**: Proper naming conventions and minimal hardcoding

### Locator Strategy
The page object uses multiple locator strategies for robust element identification:
```java
@FindBy(id = "playvox_client_id")
@FindBy(name = "client_id")
@FindBy(css = "input[placeholder*='Client ID']")
@FindBy(xpath = "//input[contains(@placeholder, 'Client ID')]")
```

### Error Handling
- Graceful handling of missing elements
- Multiple fallback locators
- Meaningful error messages
- Proper exception handling

## 📊 Test Data Management

Test data is centralized in `TestDataProvider.java`:

```java
// Valid test data
public static final String VALID_CLIENT_ID = "test_client_12345";
public static final String VALID_CLIENT_SECRET = "test_secret_abcde";

// Invalid test data  
public static final String INVALID_CLIENT_ID = "invalid_client";

// Expected messages
public static final String MANDATORY_FIELDS_ERROR = "Please fill the mandatory fields";
```

## 🔧 Configuration

Configuration is managed through `config.properties`:

```properties
# Browser settings
default.browser=chrome
headless.mode=false

# Timeouts
implicit.wait.timeout=10
explicit.wait.timeout=15

# URLs
playvox.integration.url=https://rcktechiees-874988466824422142.myfreshworks.com/...
```

## 📈 Reporting

Test results are generated using:
- **Maven Surefire Reports**: `target/surefire-reports/`
- **TestNG Reports**: `target/surefire-reports/testng-results.xml`
- **ExtentReports**: Enhanced HTML reports (can be configured)

## 🐛 Troubleshooting

### Common Issues

1. **WebDriver Not Found**
   ```bash
   # WebDriverManager automatically downloads drivers, but if issues persist:
   mvn clean install -U
   ```

2. **Page Not Loading**
   ```bash
   # Check if the URL is accessible and increase timeouts in config.properties
   page.load.timeout=60
   ```

3. **Element Not Found**
   ```bash
   # The page object uses multiple locator strategies for resilience
   # Check browser console for JavaScript errors
   ```

4. **Tests Failing Due to Timing**
   ```bash
   # Increase explicit wait timeouts
   explicit.wait.timeout=20
   ```

## 🌐 Cross-Browser Testing

The framework supports multiple browsers:

```bash
# Chrome (default)
mvn test -Dbrowser=chrome

# Firefox  
mvn test -Dbrowser=firefox

# Edge
mvn test -Dbrowser=edge
```

## 📝 Adding New Tests

To add new test cases:

1. **Create new test class** extending `BaseTest`
2. **Use Page Object** methods for interactions
3. **Follow naming convention**: `SC###_DescriptiveNameTest.java`
4. **Add meaningful test steps** using `logTestStep()`
5. **Include assertions** for verification
6. **Update TestNG suite** configuration if needed

Example:
```java
@Test(description = "TC### - Description")
public void testMethodName() {
    logTestStep("Starting TC### - Description");
    
    // Test implementation
    integrationPage.enterClientId("test_value");
    integrationPage.clickAuthorizeAccess();
    
    // Assertions
    Assert.assertTrue(integrationPage.isSuccessMessageDisplayed(), 
        "Success message should be displayed");
    
    logTestStep("TC### completed successfully");
}
```

## 🔄 Continuous Integration

This project includes a GitHub Actions CI/CD pipeline that automatically builds and tests the code on every push and pull request.

### CI Workflow Features

The CI workflow (`.github/workflows/ci.yml`) includes:

- **Java 11 Setup**: Automatically configures the correct Java version
- **Chrome Browser**: Sets up Chrome for Selenium testing
- **Maven Caching**: Caches dependencies for faster builds
- **Automated Testing**: Runs all TestNG test suites
- **Test Reporting**: Generates detailed test reports with JUnit XML format
- **Artifact Upload**: Saves test results and reports for review

### Workflow Triggers

The CI runs on:
- Push to `main` or `master` branches
- Pull requests targeting `main` or `master` branches

### Test Reports

After each CI run, you can view:
- **GitHub Actions Summary**: Overall pass/fail status
- **Test Reporter**: Detailed test results with failure details
- **Artifacts**: Downloadable test reports and surefire outputs

### Running Tests Locally

To run tests the same way as CI:

```bash
# Clean build and test
mvn clean compile test

# Run with headless Chrome (CI mode)
HEADLESS=true mvn test
```

### CI Status

Check the CI status badge and recent workflow runs in the "Actions" tab of this repository.

## 🤝 Contributing

1. Follow the existing code structure and naming conventions
2. Ensure all tests have proper documentation
3. Use explicit waits instead of Thread.sleep
4. Add meaningful assertions and error messages
5. Test with multiple browsers before submitting

## 📄 License

This project is part of the PlayVox integration testing suite.

---

For questions or issues, please refer to the test documentation or create an issue in the repository.
