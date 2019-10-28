/**
 * 
 */
package com.asms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

/**
 * @author ashokp
 *
 */
public class LoginPage 
{
	WebDriver driver;
	String expValue = "AviatorSMS";
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	@FindBy(name="txt_Username") WebElement username;
	@FindBy(name="txt_Password") WebElement password;
	@FindBy(name="btn_Login") WebElement login;
	
	
	public void loginToASMS(String appUserName, String appPassword)
	{
		username.clear();
		username.sendKeys(appUserName);
		password.clear();
		password.sendKeys(appPassword);
		login.click();
		String actValue = driver.getTitle();
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertEquals(actValue, expValue);
		softAssertion.assertAll();
	}
	
	
	

}
