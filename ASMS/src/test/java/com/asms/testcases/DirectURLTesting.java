package com.asms.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.asms.pages.BaseClass;
import com.asms.utility.ExcelDataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author ashokp
 *
 */

public class DirectURLTesting extends BaseClass
{
	String browserName="Chrome";
	//public WebDriver driver;
	
	@DataProvider(name="directURL")
	public static Object[][] getData()
	{
		return ExcelDataProvider.readExcelData();
	}
	
	@Test(dataProvider="directURL")
	public void directASMS_URL_Testing(String url)
	{
		System.out.println("Application url:"+url);
		logger = report.createTest("Direct URL Testing");
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("IE"))
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		
		else
		{
			System.out.println("We dont support this browser");
		}
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		String URL_title = driver.getTitle();
		System.out.println("Title is:"+URL_title);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(URL_title, "Welcome to Aviator SMS");
		logger.pass("URL is---> "+url);
		softAssert.assertAll();
	}
	
	

}
