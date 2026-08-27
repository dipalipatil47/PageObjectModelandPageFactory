package com.makemytrip.testcases;

import org.testng.annotations.Test;

import com.makemytrip.base.BaseTest;
import com.makemytrip.pages.HomePage;

public class FlightPageTest extends BaseTest {

	@Test
	public void searchFlight() {

		HomePage home = new HomePage();
		home.selectFlightMenu();

		home.roundTripMenu();
		home.enterDepartureCity();
		home.enterReturnCity();
		home.enterDepartureDate();
		home.enterReturnDate();
		home.searchButton();
	}

}
