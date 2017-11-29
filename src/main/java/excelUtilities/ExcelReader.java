package excelUtilities;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.collections.CollectionUtils;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import driver.Global;
import webUtilities.Elements;

public class ExcelReader {
	
	private static final String LOCATOR_VALUE = "locatorValue";
	private static final String LOCATOR_TYPE = "locatorType";
	private static final String UI_OBJ_NAME = "uiObjName";
	Elements commonWeb = new Elements();
	List<String> errorLocatorList = new ArrayList<>();

	public List<HashMap<String, List<LinkedHashMap<String, String>>>> loadExcelData(File fileName) {
		String sheetName;
		FileInputStream fis = null;
		List allSheetData = Lists.newArrayList();
		Iterator rows;
		XSSFSheet sheet;
		try {
			fis = new FileInputStream(fileName);
			// Create an excel workbook from the file system
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			// Get the first sheet on the workbook.
			Map sheetData = Maps.newHashMap();
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				sheet = workBook.getSheetAt(i);
				sheetName = workBook.getSheetName(i);
				rows = sheet.rowIterator();
				List<Object> sheetHeaders = Lists.newArrayList();
				List rowList = null;
				rowList = Lists.newArrayList();
				Map<Object, Object> rowData = null;
				while (rows.hasNext()) {
					XSSFRow row = (XSSFRow) rows.next();
					Iterator cells = row.cellIterator();
					if (row.getRowNum() == 0) {
						while (cells.hasNext()) {
							XSSFCell cell = (XSSFCell) cells.next();
							cell.setCellType(Cell.CELL_TYPE_STRING);
							sheetHeaders.add(cell);
						}
					} else {
						Iterator headerCells = sheetHeaders.iterator();
						rowData = Maps.newLinkedHashMap();
						while (cells.hasNext()) {
							XSSFCell cell = (XSSFCell) cells.next();
							cell.setCellType(Cell.CELL_TYPE_STRING);
							rowData.put(headerCells.next(), cell);
							
						}
						rowList.add(rowData);
					}
				}
				sheetData.put(sheetName, rowList);

			}
			allSheetData.add(sheetData);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return allSheetData;

	}

	public List<HashMap<String, List<LinkedHashMap<String, String>>>> loadObjectsFromExcel(File fileName) {
		String sheetName;
		FileInputStream fis = null;
		List allSheetData = Lists.newArrayList();
		Iterator rows;
		XSSFSheet sheet;
		try {
			fis = new FileInputStream(fileName);
			// Create an excel workbook from the file system
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			// Get the first sheet on the workbook.
			Map sheetData = Maps.newHashMap();
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				sheet = workBook.getSheetAt(i);
				sheetName = workBook.getSheetName(i);
				rows = sheet.rowIterator();
				List<Object> sheetHeaders = Lists.newArrayList();
				List rowList = null;
				rowList = Lists.newArrayList();
				Map<Object, Object> rowData = null;
				while (rows.hasNext()) {
					XSSFRow row = (XSSFRow) rows.next();
					Iterator cells = row.cellIterator();
					if (row.getRowNum() == 0) {
						while (cells.hasNext()) {
							XSSFCell cell = (XSSFCell) cells.next();
							cell.setCellType(Cell.CELL_TYPE_STRING);
							sheetHeaders.add(cell);
						}
					} else {
						Iterator headerCells = sheetHeaders.iterator();
						rowData = Maps.newLinkedHashMap();
						while (cells.hasNext()) {
							XSSFCell cell = (XSSFCell) cells.next();
							cell.setCellType(Cell.CELL_TYPE_STRING);
							rowData.put(headerCells.next(), cell);
						}
						rowList.add(rowData);
						By by = commonWeb.byLocator(rowData.get(LOCATOR_TYPE).toString(), rowData.get(LOCATOR_VALUE).toString());
						Global.locatorProps.put(rowData.get(UI_OBJ_NAME).toString(), by);
						if(by == null) {
							errorLocatorList.add(rowData.get(UI_OBJ_NAME).toString());
						}
					}
				}
				sheetData.put(sheetName, rowList);
				validateLocatorTypes();

			}
			allSheetData.add(sheetData);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return allSheetData;

	}

	private void validateLocatorTypes() {
		// TODO add logger and reporting
		if(!CollectionUtils.hasElements(errorLocatorList)) {
			assertTrue(false);
		}
	}
}