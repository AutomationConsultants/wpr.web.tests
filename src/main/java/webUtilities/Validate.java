package webUtilities;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import driver.Global;

public class Validate {
	
	private static Logger logger = LogManager.getRootLogger();

	public boolean linkContains(String uiObjectName, String expected) {
		boolean linkContains = false;
		WebElement element = Global.link.get(uiObjectName);
		String linkText = null;
		if(element != null) {
			linkText = element.getAttribute("href");
			if(StringUtils.contains(linkText, expected)) {
				linkContains = true;
				logger.info(uiObjectName + " has link- " + linkText);
			} else {
				logger.info(uiObjectName + "link does not contain text " + expected + ". Link text is " + linkText);
			}
		}
		return linkContains;
	}

	public boolean isElementDisplayed(String uiObjectName) {
		try {
			return Global.wait.fluentForElement(uiObjectName).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
	}

	public boolean isElementDisplayedInSecs(String uiObjectName, int seconds) {
		try {
			return Global.wait.fluentForElementForSecs(uiObjectName, seconds).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
	}

	public boolean isElementDisplayedImmediately(String uiObjectName) {
		try {
			return Global.wait.fluentForElementWithoutWait(uiObjectName).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
	}
	
	public boolean textOfInput(String uiObjectName, String expected) {
		boolean expActualSame = false;
		String actual = Global.inputfield.getText(uiObjectName);
		if(actual != null && expected.trim().equalsIgnoreCase(actual.trim())) {
			expActualSame = true;
			logger.info(uiObjectName + " has text value " + expected);
		} 
		return expActualSame;
	}
	
	public boolean textOfDropdown(String uiObjectName, String expected) {
		WebElement element = Global.dropdown.getSelectedOption(uiObjectName);
		String actual = null;
		boolean textOfDropdown = false;
		if(element != null) {
			actual = element.getText();
			if(actual != null && expected.trim().equalsIgnoreCase(actual.trim())) {
				textOfDropdown = true;
				logger.info(uiObjectName + " has text value " + expected);
			}
		}
		return textOfDropdown;
	}
	
	public boolean isRadioSelected(String uiObjectName) {
		boolean isRadioSelected = false;
		WebElement button = Global.elements.object(uiObjectName);
		try {
			if(button.isSelected()) {
				isRadioSelected = true;
				logger.info(uiObjectName + " radio button is selected");
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return isRadioSelected;
	}

	public boolean isRadioNotSelected(String uiObjectName) {
		boolean isRadioNotSelected = false;
		WebElement button = Global.elements.object(uiObjectName);
		try {
			if(!button.isSelected()) {
				isRadioNotSelected = true;
				logger.info(uiObjectName + " radio button is not selected");
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return isRadioNotSelected;
	}
	
	public boolean isChechboxSelected(String uiObjectName) {
		return isRadioSelected(uiObjectName);
	}
	
	public boolean isCheckboxNotSelected(String uiObjectName) {
		return isRadioNotSelected(uiObjectName);
	}
	
	public boolean isTextPresentOnPage(String textToFind) {
		boolean isTextPresentOnPage = false;
		try {
			if (Global.elements.findByTextOnPage(textToFind).isDisplayed()) {
				isTextPresentOnPage = true;
				logger.info(textToFind + " is present on the page");
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return isTextPresentOnPage;
	}
	
	public boolean verifyTextPresent(String textToVerify) {
		WebElement textElement = Global.elements.findSimilarByTextOnPage(textToVerify);
		if(textElement != null) {
			logger.info("Similar to " + textToVerify + " is present on the page");
			return true;
		} else {
			return false;
		}
	}

}
