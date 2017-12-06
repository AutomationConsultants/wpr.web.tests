package impl;

import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;  

import driver.Global;

public class LandingPage {
	
	public boolean isLeftNavOpen() {
		return Global.validate.isTextPresentOnPage(" Group Management For: ");
	}
	
	public void validateOptionOnLeftNav(String optionName) {
		assertThat(Global.validate.isTextPresentOnPage(optionName)).isTrue();
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
