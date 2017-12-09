package webUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import driver.Global;

public class Checkbox {

	private static Logger logger = LogManager.getRootLogger();

	public void check(String uiObjectName) {
		WebElement element = null;
		try {
			element = Global.elements.object(uiObjectName);
			if (element != null && !element.isSelected()) {
				element.click();
			}
		} catch (Exception e) {

		}
	}

	public void unCheck(String uiObjectName) {
		WebElement element = null;
		try {
			element = Global.elements.object(uiObjectName);
			if (element != null && element.isSelected()) {
				element.click();
			}
		} catch (Exception e) {

		}
	}

}
