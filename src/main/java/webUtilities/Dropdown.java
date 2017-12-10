package webUtilities;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import driver.Global;

public class Dropdown {
	
	private static Logger logger = LogManager.getRootLogger();

	public void selectByIndex(String uiObjectName, int index) {
		Select select = null;
		try {
			select = new Select(Global.elements.object(uiObjectName));
			select.selectByIndex(index);
			logger.info("Selected element " + index + " of dropdown " + uiObjectName);
		} catch (Exception e) {
			logger.info(e);
		}
	}

	public void selectByValue(String uiObjectName, String value) {
		Select select = null;
		try {
			select = new Select(Global.elements.object(uiObjectName));
			select.selectByValue(value);
			logger.info("Selected " + value + " of the dropdown " + uiObjectName);
		} catch (Exception e) {
			logger.info(e);
		}
	}

	public void selectByVisibleText(String uiObjectName, String visibleText) {
		Select select = null;
		try {
			select = new Select(Global.elements.object(uiObjectName));
			select.selectByVisibleText(visibleText);
			logger.info("Selected " + visibleText + " of the dropdown " + uiObjectName);
		} catch (Exception e) {
			logger.info(e);
		}
	}

	public List<WebElement> getAllSelectedOptions(String uiObjectName) {
		Select select = null;
		List<WebElement> listOfSelectedElements = null;
		try {
			select = new Select(Global.elements.object(uiObjectName));
			listOfSelectedElements = select.getAllSelectedOptions();
			logger.info("Selected options of the " + uiObjectName + "dropdown are: " + listOfSelectedElements);
			return listOfSelectedElements;
		} catch (Exception e) {
			logger.info(e);
			return null;
		}
	}

	public WebElement getSelectedOption(String uiObjectName) {
		Select select = null;
		WebElement selectedElement = null;
		try {
			select = new Select(Global.elements.object(uiObjectName));
			selectedElement = select.getFirstSelectedOption();
			logger.info(selectedElement + " is selected of the dropdown " + uiObjectName);
			return selectedElement;
		} catch (Exception e) {
			logger.info(e);
			return null;
		}
	}
	
	public void selectWithJs(int index) {
//		Global.jse.executeScript("return document.getElementById('id').selectedIndex = '" + index + "';");
	}

}
