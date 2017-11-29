package excelUtilities;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

public class TestExcel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExcelReader excelReader = new ExcelReader();
		File file = new File("/Users/aishvaryakapoor/Desktop/Data.xlsx");
		
		//HashMap excelData = excelReader.loadExcelLines(file);
		//List excelData1 = Lists.newArrayList();
		//excelData.get("Sheet1");
		//System.out.println(excelReader.loadExcelData(file));
		List excelData = Lists.newArrayList();
		excelData = excelReader.loadExcelData(file);
		System.out.println(excelData);
		
	}

}
