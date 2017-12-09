package impl;

import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import driver.Global;

public class LandingPage {
	
	private static Logger logger = LogManager.getRootLogger();
	
	public boolean isLeftNavOpen() {
		return Global.validate.isTextPresentOnPage(" Group Management For: ");
	}
	
	public boolean isRightNavOpen() {
		return Global.validate.isElementDisplayed("lstRightnavAcc");
	}
	
	public boolean validateOptionOnLeftNav(String optionName) {
		return Global.validate.isTextPresentOnPage(optionName);
	}
	
	public void validateLeftNavOptions() {
		validateOptionOnLeftNav("Dashboard");
		validateOptionOnLeftNav("Detail");
		validateOptionOnLeftNav("Sub-Groups");
		validateOptionOnLeftNav("Admins");
		validateOptionOnLeftNav("Pet Owners");
		validateOptionOnLeftNav("Pets");
		validateOptionOnLeftNav("Waste Sample Results");
		validateOptionOnLeftNav("Parentage Results");
	}
	
	public void closeRightNavIfOpen() {
		if(isRightNavOpen()) {
			clickOnRightNavButton();
		}
	}
	
	public void openRightNavIfClosed() {
		if(!isRightNavOpen()) {
			clickOnRightNavButton();
		}
	}
	
	public void clickOnRightNavButton() {
		Global.elements.object("btnRightNav").click();
	}
	

}
