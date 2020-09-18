package com.amazon.GenericFunctions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestUtil  {
	public static Xls_Reader excel =null;
	
public static String path = "./XLFile/Amazon_Details.xlsx" ;
	
public static String mailscreenshotpath;


	public static String generateTimeStamp(){
		 
		Calendar cal = new GregorianCalendar();
		  int month = cal.get(Calendar.MONTH); //3
		  int year = cal.get(Calendar.YEAR); //2014
		  int sec =cal.get(Calendar.SECOND);
		  int min =cal.get(Calendar.MINUTE);
		  int date = cal.get(Calendar.DATE);
		  int day =cal.get(Calendar.HOUR_OF_DAY);
		
		String timestamp = year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec;
		return timestamp;
	}

	
	public static boolean isExecutable(String tcid){
		
		for(int rowNum=2; rowNum<=excel.getRowCount("Credentials"); rowNum++){
			
			if(excel.getCellData("Credentials", "TestCase_Name", rowNum).equals(tcid)){
				if(excel.getCellData("Credentials", "runmode", rowNum).equalsIgnoreCase("Y")){
					
					return true;
					
				}else{
					
					return false;
				}
			
			}
		
		}
		
		return false;
		
	}
	
	
	
	
	
	public static Object[][] getData(String sheetName){
		
		
		
		int rows = excel.getRowCount(sheetName);
		int cols= excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){ //2
			
			
			
			for(int colNum=0 ; colNum< cols; colNum++){
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum); //-2
			}
		}
		
		return data;
		
		
		
	}
	
	public static void zip(String filepath){
	 	try
	 	{
	 		File inFolder=new File(filepath);
	 		File outFolder=new File("Reports.zip");
	 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
	 		BufferedInputStream in = null;
	 		byte[] data  = new byte[1000];
	 		String files[] = inFolder.list();
	 		for (int i=0; i<files.length; i++)
	 		{
	 			in = new BufferedInputStream(new FileInputStream
	 			(inFolder.getPath() + "/" + files[i]), 1000);  
	 			out.putNextEntry(new ZipEntry(files[i])); 
	 			int count;
	 			while((count = in.read(data,0,1000)) != -1)
	 			{
	 				out.write(data, 0, count);
	 			}
	 			out.closeEntry();
  }
	 		out.flush();
	 		out.close();
	 	
}
  catch(Exception e)
  {
	  e.printStackTrace();
  } 
 }	
//--------------------------------------Read Data From Excel------------------------------------	
	public static String[][] GetValue(String Pathfile, String sheetName, int startrow) throws IOException
	{
	   File excel= new File(Pathfile);
	   FileInputStream fis = new FileInputStream(excel);
	   @SuppressWarnings("resource")
	 XSSFWorkbook wb = new XSSFWorkbook(fis);
	   XSSFSheet ws = wb.getSheet(sheetName);
	  // System.out.println(startrow);
	   int colNum = ws.getRow(startrow).getLastCellNum();
	  // System.out.println(colNum);
	   String [][] arrays = new String [1][colNum];
	   for(int i=0;i<colNum;i++){
	    XSSFRow row= ws.getRow(startrow);
	    XSSFCell cell = row.getCell(i);
	    arrays[0][i] = cellToString(cell);
	   // System.out.println(arrays[0][i]);
	  }
	   return arrays;
	  }

//	   private static String cellToString(XSSFCell cell) {
//	   Object result;
//	   int type = cell.getCellType();
//	 
//	   switch(type)
//	   {
//	   case 0:
//	    result = cell.getNumericCellValue();
//	    break;
//	   case 1:
//	    result = cell.getStringCellValue();
//	    break;
//	   default:
//	    throw new RuntimeException("there are no support for this type of cell");
//	   }
	private static String cellToString(XSSFCell cell) {
	    Object result;
	    int type;
	    try{
	     type = cell.getCellType();
	    }catch (NullPointerException e) {
	     type = 2;
	    }
	  
	    switch(type)
	    {
	    case Cell.CELL_TYPE_NUMERIC:
	     DataFormatter formatter = new DataFormatter();
	     result = formatter.formatCellValue(cell);
	     break;
	    case Cell.CELL_TYPE_STRING:
	     result = cell.getStringCellValue();
	     break;
	    case Cell.CELL_TYPE_BLANK:
	     result = "";
	     break;
	    default:
	     throw new RuntimeException("there are no support for this type of cell");
	    }
//	   
	   return result.toString();

	 }	
	


}
