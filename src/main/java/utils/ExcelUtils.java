package utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   

   String[][] tabArray = null;

   try {

	   FileInputStream ExcelFile = new FileInputStream(FilePath);

	   // Access the required test data sheet

	   ExcelWBook = new XSSFWorkbook(ExcelFile);

	   ExcelWSheet = ExcelWBook.getSheet(SheetName);

	   int startRow = 1;

	   int startCol = 0;

	   int ci,cj;

	   //first row is header so i have substracted one row
	   int totalRows = getLastTestDataRowNum()-1;
	   

	   // you can write a function as well to get Column count

	   int totalCols = ExcelWSheet.getRow(0).getLastCellNum();
	   

	   tabArray=new String[totalRows][totalCols];

	   ci=0;

	   boolean flag=false;
	   for (int i=startRow;i<=totalRows;i++, ci++) {           	   

		  cj=0;
		  if(flag==true)
		  {
			  break;
		  }
		  

		  
		   for (int j=startCol;j<totalCols;j++, cj++){

			   tabArray[ci][cj]=getCellData(i,j);
			   if(tabArray[ci][cj].equalsIgnoreCase(""))
			   {
				   flag=true;
				   break;
			   }

			   System.out.println(tabArray[ci][cj]);  

				}

			}

		}

	catch (FileNotFoundException e){

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

		}

	catch (IOException e){

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

		}

	return(tabArray);

	}

public static int getLastTestDataRowNum()
{
	try {
		int i;
		for(i=0;i<ExcelWSheet.getLastRowNum();i++)
		{
			String val=ExcelWSheet.getRow(i).getCell(0).getStringCellValue();
			if(val.equalsIgnoreCase(""))
			{
				break;
			}
		}
		return i;
		
	} catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
}

public static String getCellData(int RowNum, int ColNum) throws Exception {

	try{

		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		
		

		int dataType = Cell.getCellType();

		if  (dataType == 3) {

			return "";

		}else{

			//String CellData = Cell.getStringCellValue();

			DataFormatter formatter = new DataFormatter();
			String CellData = formatter.formatCellValue(Cell);	
			return CellData;

		}
	}
		catch (Exception e){

		System.out.println(e.getMessage());

		throw (e);

		}

	}
	


}
