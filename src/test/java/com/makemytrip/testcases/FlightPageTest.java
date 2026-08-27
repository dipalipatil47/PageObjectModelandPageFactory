package com.makemytrip.testcases;

import org.testng.annotations.Test;

import com.makemytrip.pages.HomePage;

public class FlightPageTest extends TestSetup {

    @Test
    public void searchFlight() {

        test.info("Starting flight search");

        HomePage homePage = new HomePage();

        test.info("Selecting Flight menu");
        homePage.selectFlightMenu();

        test.info("Selecting Round Trip");
        homePage.roundTripMenu();

        test.info("Entering departure city");
        homePage.enterDepartureCity();

        test.info("Entering return city");
        homePage.enterReturnCity();

        test.info("Selecting departure date");
        homePage.enterDepartureDate();

        test.info("Selecting return date");
        homePage.enterReturnDate();

        test.info("Clicking Search button");
        homePage.searchButton();

        test.pass("Flight search completed successfully");
    }
}