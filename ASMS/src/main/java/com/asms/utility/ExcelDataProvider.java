/**
 * 
 */
package com.asms.utility;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author ashokp
 *
 */
public class ExcelDataProvider 
{
	public static Workbook wb;
	public static Object[][] readExcelData() 
	{
		try 
		{
			FileInputStream fis = new FileInputStream("./TestData/TestData.xlsx");
			wb = WorkbookFactory.create(fis);
		}
		catch (Exception e) 
		{
			System.out.println("Unable to read Excel file"+e.getMessage());
		} 
		Sheet sh = wb.getSheet("AppURL_MPL");
		int rowCount = sh.getLastRowNum();
		int colCount = sh.getRow(0).getLastCellNum();
		System.out.println("rows & cols"+rowCount+"&"+colCount);
		String[][] data = new String[rowCount][colCount];
		for(int row=1;row<=rowCount;row++)
		{
			Row r = sh.getRow(row);
			for (int cell=0;cell<colCount;cell++)
			{
				data[row-1][cell] = r.getCell(cell).getStringCellValue();
				System.out.println(data[row-1][cell]);
			}
		}
		return data;
	}

}
