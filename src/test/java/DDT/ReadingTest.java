/*
 * package DDT;
 * 
 * import java.io.FileInputStream; import java.io.FileNotFoundException; import
 * java.io.IOException;
 * 
 * import org.apache.poi.EncryptedDocumentException; import
 * org.apache.poi.sl.usermodel.*; import org.apache.poi.sl.usermodel.Sheet;
 * import org.apache.poi.ss.usermodel.*; import
 * org.apache.poi.ss.usermodel.WorkbookFactory; import
 * org.apache.poi.xssf.usermodel.XSSFWorkbook; import
 * org.openqa.selenium.WebDriver;
 * 
 * public class ReadingTest { WebDriver driver; static String
 * path1="testdata.xlsx";
 * 
 * public static void main(String[] args) {
 * 
 * 
 * 
 * 
 * } public static Object[][] gettestdata(String path1,String sheetname){
 * 
 * FileInputStream fis=new FileInputStream(path1); Workbook
 * workbook=WorkbookFactory.create(fis); Sheet
 * sheet=workbook.getSheet(sheetname);
 * 
 * int rowCount=sheet.getLastRowNum(); int
 * colCount=sheet.get(0).getLastcolnum();
 * 
 * for(in i=i;i<=rowCount;i++) { Row row=sheet.getrow(i); for(int
 * j=1;j<colr,j++) { cell=row.getcall(j); ob[][]=cell.tosteing();
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 * 
 * 
 * 
 * }
 * 
 * 
 * }
 */