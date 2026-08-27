package com.makemytrip.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {

    public static Properties config;
    public static WebDriver driver;

    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    // -------------------------------
    // Initialize Extent Report
    // -------------------------------

    @BeforeSuite
    public void setExtentReport() {

        htmlReporter = new ExtentSparkReporter(
                System.getProperty("user.dir")
                        + "/extentReports/MakeMyTripReport.html"
        );

        htmlReporter.config().setDocumentTitle(
                "MakeMyTrip Automation Report"
        );

        htmlReporter.config().setReportName(
                "Functional Testing"
        );

        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Host Name", "LocalHost");
        extent.setSystemInfo("Environment", "Stage");
        extent.setSystemInfo("Browser", "Chrome");
    }

    // -------------------------------
    // Driver Initialization
    // -------------------------------

    @BeforeMethod
    public void driverInitialization() throws IOException {

        readPropertyFile();

        driver = new ChromeDriver();

        driver.get(config.getProperty("url"));

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(
                        Integer.parseInt(
                                config.getProperty("pageloadTime")
                        )
                )
        );

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(20)
        );

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();

        // Create Extent Test
        test = extent.createTest("Search Flight Test");

        test.info("Chrome browser launched successfully");
        test.info("Application URL opened successfully");
    }

    // -------------------------------
    // Read Property File
    // -------------------------------

    public void readPropertyFile() throws IOException {

        try {

            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir")
                            + "\\src\\test\\resources\\property\\config.properties"
            );

            config = new Properties();

            config.load(fis);

            fis.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }
}