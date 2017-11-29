package webUtilities;

import driver.Global;

public class InputField {

	Elements common = new Elements();

	public void setText(String uiObjectName, String text) {
		try {
			common.object(uiObjectName).sendKeys(text);
		} catch (Exception e) {

		}
	}

	public void setPassword(String uiObjectName, String password) {
		try {
			common.object(uiObjectName).sendKeys(password);
		} catch (Exception e) {

		}
	}

	public String getText(String uiObjectName) {
		try {
			return common.object(uiObjectName).getText();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void inputTextWithJs(String uiObjectName, String text) {
		Global.jse.executeScript("arguments[0].value='" + text + "'", common.object(uiObjectName));
	}
	
	public void inputPwdWithJs(String uiObjectName, String password) {
		Global.jse.executeScript("arguments[0].value='" + password + "'", common.object(uiObjectName));
	}
	
	public String getTextWithJs(String uiObjectName) {
		try {
			return Global.jse.executeScript("return arguments[0].text", common.object(uiObjectName)).toString();
		} catch (Exception e) {
			return null;
		}
	}
}
