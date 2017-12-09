package webUtilities;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import driver.Global;

public class Validate {

	public boolean linkContains(String uiObjectName, String expected) {
		boolean linkContains = false;
		WebElement element = Global.link.get(uiObjectName);
		String linkText = null;
		if(element != null) {
			linkText = element.getAttribute("href");
			if(StringUtils.contains(linkText, expected)) {
				linkContains = true;
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
			return false;
		}
	}

	public boolean isElementDisplayedInSecs(String uiObjectName, int seconds) {
		try {
			return Global.wait.fluentForElementForSecs(uiObjectName, seconds).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayedImmediately(String uiObjectName) {
		try {
			return Global.wait.fluentForElementWithoutWait(uiObjectName).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean textOfInput(String uiObjectName, String expected) {
		boolean expActualSame = false;
		String actual = Global.inputfield.getText(uiObjectName);
		if(actual != null && expected.trim().equalsIgnoreCase(actual.trim())) {
			expActualSame = true;
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
			}
		} catch (Exception e) {
			
		}
		return isRadioSelected;
	}

	public boolean isRadioNotSelected(String uiObjectName) {
		boolean isRadioNotSelected = false;
		WebElement button = Global.elements.object(uiObjectName);
		try {
			if(!button.isSelected()) {
				isRadioNotSelected = true;
			}
		} catch (Exception e) {
			
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
			}
		} catch (Exception e) {
		}
		return isTextPresentOnPage;

	}
	
	public boolean verifyTextPresent(String textToVerify) {
		WebElement textElement = Global.elements.findSimilarByTextOnPage(textToVerify);
		if(textElement != null) {
			System.out.println(textElement.getText());
			return true;
		} else {
			return false;
		}
	}

}
