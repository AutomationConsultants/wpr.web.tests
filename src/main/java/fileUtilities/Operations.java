package fileUtilities;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import excelUtilities.ExcelReader;

public class Operations {
	ExcelReader excel = new ExcelReader();
	
	public void loadObjectsToProperties(File fileName) {
		List<HashMap<String,List<LinkedHashMap<String,String>>>> objectsExcel = excel.loadExcelData(fileName);
//		Iterable sheetIte = objectsExcel.iterator();

	}

}
