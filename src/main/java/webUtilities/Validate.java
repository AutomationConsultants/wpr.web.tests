package webUtilities;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Validate {
	Wait wait = new Wait();
	InputField inputField = new InputField();
	Dropdown dropdown = new Dropdown();
	Elements elements = new Elements();
	Link link = new Link();
	
	public boolean linkContains(String uiObjectName, String expected) {
		boolean linkContains = false;
		WebElement element = link.get(uiObjectName);
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
			return wait.fluentForElement(uiObjectName).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayedInSecs(String uiObjectName, int seconds) {
		try {
			return wait.fluentForElementForSecs(uiObjectName, seconds).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayedImmediately(String uiObjectName) {
		try {
			return wait.fluentForElementWithoutWait(uiObjectName).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean textOfInput(String uiObjectName, String expected) {
		boolean expActualSame = false;
		String actual = inputField.getText(uiObjectName);
		if(actual != null && expected.trim().equalsIgnoreCase(actual.trim())) {
			expActualSame = true;
		} 
		return expActualSame;
	}
	
	public boolean textOfDropdown(String uiObjectName, String expected) {
		WebElement element = dropdown.getSelectedOption(uiObjectName);
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
		WebElement button = elements.object(uiObjectName);
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
		WebElement button = elements.object(uiObjectName);
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

}
