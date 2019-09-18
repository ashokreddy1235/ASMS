/**
 * 
 */
package com.asms.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.asms.pages.BaseClass;
import com.asms.pages.LoginPage;
import com.asms.utility.ExcelDataProvider;

/**
 * @author ashokp
 *
 */
public class LoginTestASMS extends BaseClass
{
	@DataProvider(name="userCredentials")
	public static Object[][] getData()
	{
		return ExcelDataProvider.readExcelData();
	}
	
	
	
	@Test(dataProvider="userCredentials")
	public void loginApp(String username,String Password,String expValue)
	{
		logger = report.createTest("Login to HRM");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		loginPage.loginToHRM(username, Password, expValue);
		logger.pass("Login Success");
		
	}

}
