package com.beyondx.vteaf.stepDefinition;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinition {
	
	static WebDriver driver = StepBase.getDriver();
	static int IMPLICIT_WAIT = 10;
	public static Scenario scenario;
	
	@Before
	 public void Before(Scenario scenario) {
		 this.scenario = scenario;
	 }
	
	public static void highLightElement(WebElement element) throws Exception {
        try {
                JavascriptExecutor js = (JavascriptExecutor)driver;

                for(int i = 0; i < 2; ++i) {
                    js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid green;');", new Object[]{element});
                    Thread.sleep(50L);
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", new Object[]{element, ""});
                    Thread.sleep(50L);
                    js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid green;');", new Object[]{element});
                    Thread.sleep(50L);
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", new Object[]{element, ""});
                    Thread.sleep(50L);
                }
        } catch (Exception var3) {
            System.out.println(var3);
        }

    }
	
	@Then("I enter '(.*)' in '(.*)' field")
	public static void i_enter_value_in_field(String value, String location) {
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		WebElement Field = driver.findElement(GetPageObjectRead.OR_GetLocators(location));
		try {
			highLightElement(Field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Field.sendKeys(value);
	}
	
	
	@Then("I click '(.*)'")
	public static void i_click(String location) {
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		WebElement Field = driver.findElement(GetPageObjectRead.OR_GetLocators(location));
		try {
			highLightElement(Field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Field.click();
	}
	
	@Then("I click '(.*)' by actions")
	public static void i_click_by_actions(String location) {
		Actions builder = new Actions(driver);
		 WebElement link_Home = driver.findElement(GetPageObjectRead.OR_GetLocators(location));
        Action mouseOverHome = builder.moveToElement(link_Home).build();
        mouseOverHome.perform();        
	}
	
	@Then("I should see '(.*)' present in page")
	public void i_should_see_element(String location) {
		WebElement element = driver.findElement(GetPageObjectRead.OR_GetLocators(location));
		element.isDisplayed();
		try {
			highLightElement(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("I wait for '(.*)' sec")
	public void i_pause_for(int sec) {
		int secs =sec*1000; 
		try {
			Thread.sleep(secs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("I select '(.*)' in '(.*)' by text")
	public void i_select_by_value_text(String value,String locator) {
		Select select = new Select(driver.findElement(GetPageObjectRead.OR_GetLocators(locator)));
		select.selectByVisibleText(value);
	}
	
	@Then("I select '(.*)' in '(.*)' by value")
	public void i_select_by_value_value(String value,String locator) {
		Select select = new Select(driver.findElement(GetPageObjectRead.OR_GetLocators(locator)));
		select.selectByValue(value);
	}

	
	@Given("I check the page title is '(.*)'")
	public void i_get_page_title(String value) {
		String pageTitle =driver.getTitle();
		Assert.assertEquals(value, pageTitle);
	}
	
	@Then("I enter random email address in field '(.*)'")
	public static void i_enter_random_email_address(String locator) {
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		WebElement Field = driver.findElement(GetPageObjectRead.OR_GetLocators(locator));
		try {
			highLightElement(Field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Random rand = new Random(); 
        int rand_int1 = rand.nextInt(100000); 
        String mailId = "vishalvrk"+rand_int1+"@testing.com";
        scenario.write("Entered Mail Id is: "+mailId);
		Field.sendKeys(mailId);
	}
	
}
