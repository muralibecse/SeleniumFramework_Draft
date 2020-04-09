package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	@DataProvider(name="testdata")
	public Object[][] MyDataProvider() throws IOException{
		return dataProviderMethod("TestData","Sheet1");
	}
	
	
	
	
	
	
	
	
	
	public Object[][] dataProviderMethod(String ExcelName,String SheetName) throws IOException {
		System.out.println("data provider loaded");
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
			//define a map
					
			for(int i = 0;i<rowCount;i++) {
				
				Map<Object,Object> dataMap = new HashMap<Object,Object>();
				for(int j = 0;j<colCount;j++) {
//					System.out.println(i+ws.getRow(0).getCell(j).toString()+"====="+j+ws.getRow(i+1).getCell(j).toString());
					dataMap.put(ws.getRow(0).getCell(j).toString(), ws.getRow(i+1).getCell(j).toString());
			}
				
				obj[i][0]=dataMap;
			}
			
	
			return obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			wb.close();
			fis.close();
			
		}
		return obj;
		
		
	}
	

}
