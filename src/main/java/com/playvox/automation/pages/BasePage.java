package com.playvox.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

/**
 * Base Page class containing common methods and utilities for all page objects
 * Implements Page Object Model (POM) design pattern
 */
public abstract class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 10;
    
    /**
     * Constructor to initialize WebDriver and WebDriverWait
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Wait for element to be visible and return it
     * @param element WebElement to wait for
     * @return WebElement once visible
     */
    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Wait for element to be clickable and return it
     * @param element WebElement to wait for
     * @return WebElement once clickable
     */
    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Wait for element to be visible by locator
     * @param locator By locator for the element
     * @return WebElement once visible
     */
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable by locator
     * @param locator By locator for the element
     * @return WebElement once clickable
     */
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Clear field and enter text with explicit wait
     * @param element WebElement to enter text into
     * @param text Text to enter
     */
    protected void clearAndEnterText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Click element with explicit wait
     * @param element WebElement to click
     */
    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element).click();
    }
    
    /**
     * Click element using JavaScript executor (for stubborn elements)
     * @param element WebElement to click
     */
    protected void clickElementJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    /**
     * Get text from element with explicit wait
     * @param element WebElement to get text from
     * @return String text content
     */
    protected String getElementText(WebElement element) {
        return waitForElementToBeVisible(element).getText();
    }
    
    /**
     * Check if element is displayed
     * @param element WebElement to check
     * @return boolean true if displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Wait for page to load completely
     */
    public void waitForPageToLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    
    /**
     * Scroll element into view
     * @param element WebElement to scroll to
     */
    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Get current page title
     * @return String page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current page URL
     * @return String current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}