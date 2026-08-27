package com.makemytrip.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.makemytrip.base.BaseTest;

public class TestSetup extends BaseTest {

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            driver = null;
        }
    }

    @AfterSuite
    public void afterSuite() {

        if (extent != null) {

            extent.flush();
        }
    }
}