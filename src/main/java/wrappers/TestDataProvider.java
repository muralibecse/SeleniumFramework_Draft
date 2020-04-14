package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	
	static String TestData = null;
	static String sheetName = null;
	
	public static void SetTestData(String TestDataPointer,String sheetNamePointer) {
		if(sheetName == null || sheetName=="") {
			sheetName="Sheet1";
		}else {
			sheetName=sheetNamePointer;
		}
		
		if(TestData == null || TestData=="") {
			TestData="TestData";
		}else {
		TestData=TestDataPointer;
		}
	}
	

	public static void SetTestData(String TestDataPointer) {
		if(sheetName == null || sheetName=="") {
			sheetName="Sheet1";
		}
		if(TestData == null || TestData=="") {
			TestData="TestData";
		}else {
		TestData=TestDataPointer;
		}
	}
	
	@DataProvider(name="testdata")
	public Object[][] MyDataProvider(Method M) throws IOException{
		if(TestData==null) {
			TestData="TestData";
		}
		
		System.out.println("asdsadasd"+dataProviderMethod(TestData,"Sheet1",M.getName()));
		return dataProviderMethod(TestData,"Sheet1",M.getName());
	}

	
	
	public Object[][] dataProviderMethod(String ExcelName,String SheetName,String TestCaseName) throws IOException {
		int ClassColumnNumber = 0;
		int ExecutionCondition = 1;
		
		FileInputStream fis = null;
		XSSFWorkbook wb = null;
		Object[][] obj = null ;
		try {
			File file = new File("./TestData/"+ExcelName+".xlsx");
			fis = new FileInputStream(file);

			wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet(SheetName);

			int rowCount = ws.getLastRowNum();
			int colCount = ws.getRow(0).getLastCellNum();


			//define a object array
			obj = new Object[rowCount][1];
			obj = new Object[rowCount][1];
			//define a map
			int i=0,j=0,counter=0;
			for( i = 0;i<rowCount;i++) {

				Map<Object,Object> dataMap = new HashMap<Object,Object>();
				for( j = 0;j<colCount;j++) {
					dataMap.put(ws.getRow(0).getCell(j).toString(), ws.getRow(i+1).getCell(j).toString());
				}
				
				if(ws.getRow(i+1).getCell(ClassColumnNumber).toString().equalsIgnoreCase(TestCaseName)
						&&ws.getRow(i+1).getCell(ExecutionCondition).toString().equalsIgnoreCase("Y")) {
					obj[i][0]=dataMap;
				}
			}
		
			return obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			wb.close();
			fis.close();

		}
		return obj;
	}


}
