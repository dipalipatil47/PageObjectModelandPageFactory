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

    // =========================================================
    // LOCATORS
    // =========================================================

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

    // Departure date
    @FindBy(xpath = "(//div[contains(@class,'dateFiled')])[1]")
    WebElement departureDrop;

    // Return date
    @FindBy(xpath = "(//div[contains(@class,'dateFiled')])[2]")
    WebElement returnDrop;

    // Dynamic departure date XPath
    String departureDate =
            "//div[contains(translate(@aria-label,' ,',''),'%replace%') " +
            "and not(@aria-disabled='true')]";

    // Dynamic return date XPath
    String returnDate =
            "//div[contains(translate(@aria-label,' ,',''),'%replace%') " +
            "and not(@aria-disabled='true')]";

    // Search button
    @FindBy(xpath = "//a[contains(@class,'widgetSearchBtn') and normalize-space()='Search']")
    WebElement searchBtn;

    // Login popup close button
    @FindBy(css = "span.commonModal__close")
    WebElement closeLoginPopupButton;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }


    // =========================================================
    // SELECT FLIGHT MENU
    // =========================================================

    public void selectFlightMenu() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        // Close popup if displayed
        closeLoginPopup();

        wait.until(ExpectedConditions.visibilityOf(flightMenu));

        // Scroll to Flight menu
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                flightMenu
        );

        try {

            wait.until(ExpectedConditions.elementToBeClickable(flightMenu));

            flightMenu.click();

        } catch (ElementClickInterceptedException e) {

            // If banner/overlay intercepts normal click,
            // use JavaScript click
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    flightMenu
            );
        }
    }


    // =========================================================
    // SELECT ROUND TRIP
    // =========================================================

    public void roundTripMenu() {

        closeLoginPopup();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(roundTripMenu));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                roundTripMenu
        );

        try {

            wait.until(ExpectedConditions.elementToBeClickable(roundTripMenu));

            roundTripMenu.click();

        } catch (ElementClickInterceptedException e) {

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    roundTripMenu
            );
        }
    }


    // =========================================================
    // ENTER DEPARTURE CITY
    // =========================================================

    public void enterDepartureCity() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(fromCityDrop));

        fromCityDrop.click();

        wait.until(ExpectedConditions.visibilityOf(searchFromCity));

        searchFromCity.sendKeys(
                config.getProperty("From")
        );

        searchFromCity.sendKeys(Keys.TAB);
    }


    // =========================================================
    // ENTER RETURN CITY
    // =========================================================

    public void enterReturnCity() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(searchToCity));

        searchToCity.sendKeys(
                config.getProperty("To")
        );

        searchToCity.sendKeys(Keys.TAB);
    }


    // =========================================================
    // ENTER DEPARTURE DATE
    // =========================================================

    public void enterDepartureDate() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.visibilityOf(departureDrop)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                departureDrop
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(departureDrop)
            );

            departureDrop.click();

        } catch (ElementClickInterceptedException e) {

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    departureDrop
            );
        }

        TestUtil date =
                TestUtil.getCurrentDateandReturnDate();

        By departureDateLocator =
                TestUtil.customXpath(
                        departureDate,
                        date.departureDate
                );

        WebElement departureDateElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                departureDateLocator
                        )
                );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                departureDateElement
        );

        departureDateElement.click();
    }


    // =========================================================
    // ENTER RETURN DATE
    // =========================================================

    public void enterReturnDate() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait until return date field is visible
        wait.until(
                ExpectedConditions.visibilityOf(returnDrop)
        );

        // Scroll return date field to CENTER of screen
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                returnDrop
        );

        // Small pause for UI/header animation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Click return date
        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(returnDrop)
            );

            returnDrop.click();

        } catch (ElementClickInterceptedException e) {

            // JavaScript fallback
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    returnDrop
            );
        }

        // Get departure and return dates
        TestUtil date =
                TestUtil.getCurrentDateandReturnDate();

        // Create dynamic locator
        By returnDateLocator =
                TestUtil.customXpath(
                        returnDate,
                        date.returnDate
                );

        // Wait for return date
        WebElement returnDateElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                returnDateLocator
                        )
                );

        // Scroll selected date to center
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                returnDateElement
        );

        // Click return date
        returnDateElement.click();
    }

    // =========================================================
    // SEARCH BUTTON
    // =========================================================

    public void searchButton() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.elementToBeClickable(searchBtn)
        );

        try {

            searchBtn.click();

        } catch (ElementClickInterceptedException e) {

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    searchBtn
            );
        }
    }


    // =========================================================
    // CLOSE LOGIN POPUP
    // =========================================================

    public void closeLoginPopup() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(5));

        try {

            WebElement closeButton =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    closeLoginPopupButton
                            )
                    );

            closeButton.click();

        } catch (TimeoutException e) {

            // Popup is not displayed.
            // Continue execution.

        } catch (Exception e) {

            // If popup is present but normal click fails,
            // try JavaScript click.
            try {

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();",
                        closeLoginPopupButton
                );

            } catch (Exception ignored) {

                // Continue execution
            }
        }
    }
}