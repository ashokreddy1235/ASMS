package com.asms.testcases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
	public static String sheetName ="Test";
	int i=0;
	//public WebDriver driver;
	
	@DataProvider(name="directURL")
	public static Object[][] getData()
	{
		return ExcelDataProvider.readExcelData(sheetName);
	}
	
	@Test(dataProvider="directURL")
	public void directASMS_URL_Testing(String url)
	{
		i=i+1;
		System.out.println("Application url:"+url);
		logger = report.createTest("Direct URL Testing Before login: "+i);
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
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
		logger.pass(i+") URL is---> "+url);
		softAssert.assertAll();
	}
	
	 /*1XX - Information related code
	 2XX - success code
	 3XX - Redirection code
	 4XX - Client error code
	 5XX - Server error code*/
	
	@Test(dataProvider="directURL")
	public void directASMS_URL_Testing_After_Login(String url)
	{
		HttpURLConnection connection = null;
		String response=null;
		int responseCode=0;
		i=i+1;
		System.out.println("Application url:"+url);
		logger = report.createTest("Direct URL Testing After login: "+i);
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
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
		driver.get("http://182.75.89.80:8090/bialelmsuat/");
		WebElement username= driver.findElement(By.name("txt_Username"));
		username.clear();
		username.sendKeys("BIAL100807");
		WebElement password= driver.findElement(By.name("txt_Password"));
		password.clear();
		password.sendKeys("welcome");
		WebElement login = driver.findElement(By.name("btn_Login"));
		login.click();
		driver.navigate().to(url);
		SoftAssert softAssert = new SoftAssert();
		try 
		{
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.connect();
			responseCode = connection.getResponseCode();
			response = connection.getResponseMessage();
			connection.disconnect();
			System.out.println(i+")"+url+"---->"+responseCode+"--"+response);
			if(responseCode==100 || responseCode==200 || responseCode==300)
			{
				softAssert.assertTrue(true);
			}
			else
			{
				softAssert.assertTrue(false);
			}
			logger.pass(i+") URL is: "+url+"--Response Code: "+responseCode+"-- Response: "+response);
			softAssert.assertAll();
		} 
		catch (IOException e) 
		{
			System.out.println(i+") URL is not available");
			softAssert.assertTrue(false);
		}
		
	}
	

}
