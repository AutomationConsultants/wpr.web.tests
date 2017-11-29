package webUtilities;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import driver.Global;

public class Dropdown {

	Elements common = new Elements();

	public void selectByIndex(String uiObjectName, int index) {
		Select select = null;
		try {
			select = new Select(common.object(uiObjectName));
			select.selectByIndex(index);
		} catch (Exception e) {

		}
	}

	public void selectByValue(String uiObjectName, String value) {
		Select select = null;
		try {
			select = new Select(common.object(uiObjectName));
			select.selectByValue(value);
		} catch (Exception e) {

		}
	}

	public void selectByVisibleText(String uiObjectName, String visibleText) {
		Select select = null;
		try {
			select = new Select(common.object(uiObjectName));
			select.selectByVisibleText(visibleText);
		} catch (Exception e) {

		}
	}

	public List<WebElement> getAllSelectedOptions(String uiObjectName) {
		Select select = null;
		try {
			select = new Select(common.object(uiObjectName));
			return select.getAllSelectedOptions();
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement getSelectedOption(String uiObjectName) {
		Select select = null;
		try {
			select = new Select(common.object(uiObjectName));
			return select.getFirstSelectedOption();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void selectWithJs(int index) {
//		Global.jse.executeScript("return document.getElementById('id').selectedIndex = '" + index + "';");
	}

}
