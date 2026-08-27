package com.makemytrip.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.makemytrip.base.BaseTest;
import com.makemytrip.util.TestUtil;

public class HomePage extends BaseTest {

    // Flight menu
	@FindBy(xpath = "//a[contains(@href,'/flights/')]")
	WebElement flightMenu;

    // Round Trip
    @FindBy(css = "li[data-cy='roundTrip']")
    WebElement roundTripMenu;

    // From city
    @FindBy(xpath = "//input[@id='fromCity']")
    WebElement fromCityDrop;

    @FindBy(xpath = "//input[@placeholder='From']")
    WebElement searchFromCity;

    // To city
    @FindBy(xpath = "//input[@placeholder='To']")
    WebElement searchToCity;

    // Departure and return date fields
    @FindBy(xpath = "(//div[contains(@class,'dateFiled')])[1]")
    WebElement departureDrop;

    @FindBy(xpath = "(//div[contains(@class,'dateFiled')])[2]")
    WebElement returnDrop;

    // Dynamic date XPath
    String departureDate =
            "//div[contains(translate(@aria-label,' ,',''),'%replace%') and not(@aria-disabled='true')]";

    String returnDate =
            "//div[contains(translate(@aria-label,' ,',''),'%replace%') and not(@aria-disabled='true')]";

    // Search button
    @FindBy(xpath = "//a[contains(@class,'widgetSearchBtn') and normalize-space()='Search']")
    WebElement searchBtn;

    // Login/signup popup close button
    @FindBy(css = "span.commonModal__close")
    WebElement closeLoginPopup;

    // Constructor
    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    // Select Flights menu
    public void selectFlightMenu() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Close login popup if it appears
        closeLoginPopup();

        // Wait until Flight menu is visible
        wait.until(ExpectedConditions.visibilityOf(flightMenu));

        // Scroll Flight menu to center of screen
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                flightMenu
        );

        // Small wait for page overlay/banner to settle
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("div.imageSlideContainer")
            ));
        } catch (TimeoutException e) {
            // Continue if banner does not disappear
        }

        try {
            // Normal Selenium click
            wait.until(ExpectedConditions.elementToBeClickable(flightMenu));
            flightMenu.click();

        } catch (ElementClickInterceptedException e) {

            // JavaScript click as fallback
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    flightMenu
            );
        }
    }

    // Select Round Trip
    public void roundTripMenu() {

        closeLoginPopup();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(roundTripMenu));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                roundTripMenu);

        wait.until(ExpectedConditions.elementToBeClickable(roundTripMenu));

        roundTripMenu.click();
    }

    // Enter departure city
    public void enterDepartureCity() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(fromCityDrop));
        fromCityDrop.click();

        wait.until(ExpectedConditions.visibilityOf(searchFromCity));

        searchFromCity.sendKeys(config.getProperty("From"));
        searchFromCity.sendKeys(Keys.TAB);
    }

    // Enter return city
    public void enterReturnCity() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(searchToCity));

        searchToCity.sendKeys(config.getProperty("To"));
        searchToCity.sendKeys(Keys.TAB);
    }

    // Enter departure date
    public void enterDepartureDate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(departureDrop));
        departureDrop.click();

        TestUtil date = TestUtil.getCurrentDateandReturnDate();

        By departureDateLocator = TestUtil.customXpath(
                departureDate,
                date.departureDate);

        WebElement departureDateElement = wait.until(
                ExpectedConditions.elementToBeClickable(departureDateLocator));

        departureDateElement.click();
    }

    // Enter return date
    public void enterReturnDate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(returnDrop));
        returnDrop.click();

        TestUtil date = TestUtil.getCurrentDateandReturnDate();

        By returnDateLocator = TestUtil.customXpath(
                returnDate,
                date.returnDate);

        WebElement returnDateElement = wait.until(
                ExpectedConditions.elementToBeClickable(returnDateLocator));

        returnDateElement.click();
    }

    // Click Search
    public void searchButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();
    }

    // Close login/signup popup if displayed
    public void closeLoginPopup() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup));
            closeLoginPopup.click();
        } catch (TimeoutException e) {
            // Popup is not displayed. Continue execution.
        }
    }
}
