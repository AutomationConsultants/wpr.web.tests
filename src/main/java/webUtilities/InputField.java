package webUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import driver.Global;

public class InputField {
	
	private static Logger logger = LogManager.getRootLogger();

	public void setText(String uiObjectName, String text) {
		try {
			Global.elements.object(uiObjectName).sendKeys(text);
			logger.info(text + " text is entered in field " + uiObjectName); 
		} catch (Exception e) {
			logger.info(e);
		}
	}

	public void setPassword(String uiObjectName, String password) {
		try {
			Global.elements.object(uiObjectName).sendKeys(password);
			logger.info("***** password is entered in field " + uiObjectName);
		} catch (Exception e) {
			logger.info(e);
		}
	}

	public String getText(String uiObjectName) {
		try {
			String text = Global.elements.object(uiObjectName).getText();
			logger.info(uiObjectName + " has value " + text);
			return text;
		} catch (Exception e) {
			logger.info(e);
			return null;
		}
	}
	
	public void inputTextWithJs(String uiObjectName, String text) {
		Global.jse.executeScript("arguments[0].value='" + text + "'", Global.elements.object(uiObjectName));
		logger.info(text + " text is entered in field " + uiObjectName);
	}
	
	public void inputPwdWithJs(String uiObjectName, String password) {
		Global.jse.executeScript("arguments[0].value='" + password + "'", Global.elements.object(uiObjectName));
		logger.info("***** password is entered in field " + uiObjectName);
	}
	
	public String getTextWithJs(String uiObjectName) {
		try {
			String text = Global.jse.executeScript("return arguments[0].text", Global.elements.object(uiObjectName)).toString();
			logger.info(uiObjectName + " has value " + text);
			return text;
		} catch (Exception e) {
			logger.info(e);
			return null;
		}
	}
}
