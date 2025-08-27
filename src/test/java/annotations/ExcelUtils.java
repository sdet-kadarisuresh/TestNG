package annotations;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.File;


public class ExcelUtils {
	
	public static Object[][] getExcelData(String filePath,String sheetName){
		Object[][] data=null;
		try {
			 FileInputStream fis = new FileInputStream(new File(filePath));
	            Workbook workbook = new XSSFWorkbook(fis);
	            Sheet sheet = workbook.getSheet(sheetName);
	            int rowCount = sheet.getPhysicalNumberOfRows();
	            int colCount = sheet.getRow(0).getLastCellNum();

	            data = new Object[rowCount-1][colCount]; // excluding header
	            for(int i=1; i<rowCount; i++) {
	                Row row = sheet.getRow(i);
	                for(int j=0; j<colCount; j++) {
	                    Cell cell = row.getCell(j);
	                    data[i-1][j] = cell.toString();
	                }
	            }
	            workbook.close();
	            fis.close();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	}