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
	public static String sheetName="AppCreds";
	@DataProvider(name="userCredentials")
	public static Object[][] getData()
	{
		return ExcelDataProvider.readExcelData(sheetName);
	}
	
	
	
	@Test(dataProvider="userCredentials")
	public void loginApp(String username,String Password)
	{
		logger = report.createTest("Login to ASMS");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		loginPage.loginToASMS(username, Password);
		logger.pass("Login Success");
		
	}

}
