package com.beyondx.vteaf.Runner;

import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.beyondx.vteaf.stepDefinition.StepBase;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(dryRun = false, plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"pretty",
		"json:src/test/java/com/TestResults/cucumber-report/cucumber_1.json" }, strict = true, junit = "--step-notifications", features = {
				"src/test/java/com/beyondx/vteaf/features" }, glue = {"com.beyondx.vteaf.stepDefinition" },
			tags = { "@Runthis", "not @Ignore" }, monochrome = true)

@RunWith(Cucumber.class)
public class Test_Runner {
	public static String URL;
	public static String BROWSER;
	public static String PLATFORM;
	public static Logger log = LogManager.getLogger(Test_Runner.class);
	
	@BeforeClass
	public static void setup() throws Exception {
		FileReader reader=new FileReader("src/test/java/com/beyondx/vteaf/config/config.properties");  
		BasicConfigurator. configure();
		Properties p=new Properties();  
		p.load(reader); 
		URL = p.getProperty("url");
		BROWSER = p.getProperty("browser");
		PLATFORM = p.getProperty("platform");
		StepBase.SetUp(URL,BROWSER,PLATFORM);
		log.info("URL: "+URL);
		log.info("Browser Name: "+BROWSER);
		log.info("Platform: "+PLATFORM);
	}
	
	@AfterClass
	public static void tearDown() {
		StepBase.tearDown();
		StepBase.GenerateCucumberReport();
	}
	
	

}
