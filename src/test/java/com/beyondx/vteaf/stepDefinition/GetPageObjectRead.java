package com.beyondx.vteaf.stepDefinition;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class GetPageObjectRead {
	
	public WebDriver driver;
	public Properties prop;
	static By by;
	public static Workbook book;
	public static Sheet sheet;
	public static Logger log = LogManager.getLogger(GetPageObjectRead.class);
	
	public final static String PAGE_OBJECTS_SHEET_PATH = "src/test/java/com/beyondx/vteaf/PageObjects/pageObjects.xlsx";
	
	public static ArrayList<String> OR_READ(String locatorName) {
		FileInputStream file = null;
		ArrayList<String> Locators = new ArrayList<String>();
		try {
			file = new FileInputStream(PAGE_OBJECTS_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			String LocatorName = sheet.getRow(i+1).getCell(0).toString().trim();
			if(locatorName.equals(LocatorName)) {
				
				String LocatorType = sheet.getRow(i+1).getCell(1).toString().trim();
				String LocatorValue = sheet.getRow(i+1).getCell(2).toString().trim();
				Locators.add(LocatorType);
				Locators.add(LocatorValue);
			}
		}
		return Locators;
	}
	
	public static By OR_GetLocators(String locatorName) {
		BasicConfigurator. configure();
		ArrayList<String> Locators = OR_READ(locatorName);
		String LocatorType = Locators.get(0).toLowerCase().toString().trim();
		String LocatorValue = Locators.get(1).toString().trim();
		log.info("Successfully found element "+LocatorType+" | "+LocatorValue);
		 if (LocatorType.equalsIgnoreCase("id")) {
	            by = By.id(LocatorValue);
	        } else if (LocatorType.equalsIgnoreCase("classname")) {
	            by = By.className(LocatorValue);
	        } else if (LocatorType.equalsIgnoreCase("name")) {
	            by = By.name(LocatorValue);
	        } else if (LocatorType.equalsIgnoreCase("linktext")) {
	            by = By.linkText(LocatorValue);
	        } else if (LocatorType.equalsIgnoreCase("partiallinktext")) {
	            by = By.partialLinkText(LocatorValue);
	        } else if (LocatorType.equalsIgnoreCase("cssselector")) {
	            by = By.cssSelector(LocatorValue);
	        } else if (LocatorType.equalsIgnoreCase("xpath")) {
	            by = By.xpath(LocatorValue);
	        } else if (LocatorType.equalsIgnoreCase("tagname")) {
	            by = By.tagName(LocatorValue);
	        }
	        return by;
	}
	
	
	public static void main(String[] args) {
//		OR_GetLocators("Search_Field");
	}
	

}
