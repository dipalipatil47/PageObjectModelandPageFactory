package com.makemytrip.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {

	public static Properties config;
	public static WebDriver driver;

	public static ExtentReporter htmtlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest childTest;

	public void driverInitialization() {

		driver = new ChromeDriver();
		driver.get(config.getProperty("url"));

		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(config.getProperty("pageloadTime"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	public void readPropertyFile() throws IOException {

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\property\\config.properties");
			config = new Properties();
			config.load(fis);
		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setExtentReport() {

	    ExtentSparkReporter htmlReporter =
	            new ExtentSparkReporter(
	                    System.getProperty("user.dir")
	                    + "/extentReports/MakeMyTripReport.html");

	    htmlReporter.config().setDocumentTitle("MakeMyTrip Automation Report");
	    htmlReporter.config().setReportName("Functional Testing");
	    htmlReporter.config().setTheme(Theme.DARK);

	    extent = new ExtentReports();

	    extent.attachReporter(htmlReporter);

	    extent.setSystemInfo("Host Name", "LocalHost");
	    extent.setSystemInfo("Environment", "Stage");
	}

}
