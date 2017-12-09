package webUtilities;

import driver.Global;

public class InputField {

	public void setText(String uiObjectName, String text) {
		try {
			Global.elements.object(uiObjectName).sendKeys(text);
		} catch (Exception e) {

		}
	}

	public void setPassword(String uiObjectName, String password) {
		try {
			Global.elements.object(uiObjectName).sendKeys(password);
		} catch (Exception e) {

		}
	}

	public String getText(String uiObjectName) {
		try {
			return Global.elements.object(uiObjectName).getText();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void inputTextWithJs(String uiObjectName, String text) {
		Global.jse.executeScript("arguments[0].value='" + text + "'", Global.elements.object(uiObjectName));
	}
	
	public void inputPwdWithJs(String uiObjectName, String password) {
		Global.jse.executeScript("arguments[0].value='" + password + "'", Global.elements.object(uiObjectName));
	}
	
	public String getTextWithJs(String uiObjectName) {
		try {
			return Global.jse.executeScript("return arguments[0].text", Global.elements.object(uiObjectName)).toString();
		} catch (Exception e) {
			return null;
		}
	}
}
