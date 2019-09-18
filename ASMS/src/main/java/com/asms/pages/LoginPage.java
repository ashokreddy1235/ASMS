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
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	@FindBy(name="txt_Username") WebElement username;
	@FindBy(name="txt_Password") WebElement password;
	@FindBy(name="btn_Login") WebElement login;
	@FindBy(id="lbl_msg") WebElement spanMessage;
	
	public void loginToHRM(String appUserName, String appPassword, String expValue)
	{
		username.clear();
		username.sendKeys(appUserName);
		password.clear();
		password.sendKeys(appPassword);
		login.click();
		String actValue = spanMessage.getText();
		System.out.println("Span Message:"+actValue);
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertEquals(actValue, expValue);
		softAssertion.assertAll();
	}
	
	
	

}
