package webUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import driver.Global;

public class Button {
	
	private static Logger logger = LogManager.getRootLogger();

	public void click(String uiObjectName) {
		try {	
			Global.elements.object(uiObjectName).click();
		} catch (Exception e) {

		}
	}
	
	public void clickWithJs(String uiObjectName) {
		Global.jse.executeScript("arguments[0].click();", Global.elements.object(uiObjectName));
	}
	
}
