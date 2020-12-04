package com.beyondx.vteaf.stepDefinition;


import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class StepBase {
	public static WebDriver driver;
	public static Properties prop;
	public static String REPORT ="file://"+System.getProperty("user.dir")+"/src/test/java/com/TestResults/cucumber-output/cucumber-html-reports/overview-features.html";
	
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void SetUp(String Url,String browserName,String Platform) {
		if(browserName.equalsIgnoreCase("chrome")&& Platform.equalsIgnoreCase("desktop")) {
			System.setProperty("webdriver.chrome.driver", "src/test/java/com/beyondx/vteaf/resources/chromedriver");
			ChromeOptions Options = new ChromeOptions();
	         Options.setCapability("acceptSslCerts", true);
				driver= new ChromeDriver(Options);
				driver.manage().window().maximize();
				driver.get(Url);
			}
		if(browserName.equalsIgnoreCase("NA") && Platform.equalsIgnoreCase("rest-service")) {
			System.out.println("Initiating Rest Service");
		}
		}
	
	
	
	public static void tearDown() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			driver.quit();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Driver Not Opened");
		}
		
	}
	
	public static void GenerateCucumberReport() {
		DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd__HH_mm_ss");
		Date date = new Date();
		String timeStamp = dateFormat.format(date);
		File reportOutputDirectory = new File("src/test/java/com/TestResults/cucumber-output_"+timeStamp);
		ArrayList jsonFiles = new ArrayList();
		jsonFiles.add("src/test/java/com/TestResults/cucumber-report/cucumber_1.json");
		String buildNumber = "1";
		String projectName = "cucumberProject";
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		Reportable result = reportBuilder.generateReports();
		System.out.println("Cucumber Report Generated...");
	}
	
	public static Properties LoadConfigFile() throws Exception {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/com/beyondx/vteaf/config/config.properties");
		prop.load(fis);
		return prop;
		
	}
	
}
