package utilities;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.io.File;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider; 

public class ReadTestDataExcel {
	
	//public static void main(String[] args) throws Exception {
		//ReadTestDataExcel read = new ReadTestDataExcel();
		//read.gettestdata("NavigateToLearnHTML");
		
	//}
	@DataProvider(name="bvttestdata")
	public String[][] gettestdata(Method m) throws Exception{
		String excelSheetName=m.getName();
		File testdataxls = new File(System.getProperty("user.dir")+"//src//test//resources//testdata//testdata.xlsx");
		FileInputStream fis = new FileInputStream(testdataxls);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheetName = wb.getSheet(excelSheetName);
		int totalRows = sheetName.getLastRowNum();
		Row rowcells = sheetName.getRow(0);
		int totalCols = rowcells.getLastCellNum();
		String testData[][] = new String[totalRows][totalCols];
		DataFormatter format = new DataFormatter();
		for (int i = 1;i<=totalRows;i++) {
			for (int j =0;j<totalCols;j++) {
				testData[i-1][j]=format.formatCellValue(sheetName.getRow(i).getCell(j));
			}
		}
		return testData;
	}
}
		
		
		
		
		
	


 