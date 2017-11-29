package webUtilities;

import driver.Global;

public class Button {
	Elements common = new Elements();

	public void click(String uiObjectName) {
		try {
			common.object(uiObjectName).click();
		} catch (Exception e) {

		}
	}
	
	public void clickWithJs(String uiObjectName) {
		Global.jse.executeScript("arguments[0].click();", common.object(uiObjectName));
	}
	
}
