/**
 * 
 */
package com.asms.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author ashokp
 *
 */
public class ConfigDataProvider 
{
	Properties pro;
	public ConfigDataProvider()
	{
		File src = new File("./Config/Config.properties");
		try 
		{
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} 
		catch (Exception e) 
		{
			System.out.println();
		} 
	}
	
	
	public String getDataFormConfig(String keyToSearch)
	{
		return pro.getProperty(keyToSearch);
	}
	
	public String getBrowser()
	{
		return pro.getProperty("Browser");
	}
	
	public String getUrl()
	{
		return pro.getProperty("qaUrl");
	}

}
