package webUtilities;

import org.openqa.selenium.WebElement;

public class Checkbox {

	Elements common = new Elements();

	public void check(String uiObjectName) {
		WebElement element = null;
		try {
			element = common.object(uiObjectName);
			if (element != null && !element.isSelected()) {
				element.click();
			}
		} catch (Exception e) {

		}
	}

	public void unCheck(String uiObjectName) {
		WebElement element = null;
		try {
			element = common.object(uiObjectName);
			if (element != null && element.isSelected()) {
				element.click();
			}
		} catch (Exception e) {

		}
	}

}
