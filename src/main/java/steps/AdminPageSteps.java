package steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.Global;

public class AdminPageSteps {
	
	@Then("^validate that the following fields are displayed$")
	public void validateThatTheFollowingFieldsAreDisplayed(List<String> fieldList) {
	    for (String fieldNm : fieldList) {
			assertThat(Global.validate.isElementDisplayed(fieldNm)).as(fieldNm + " is not displayed").isTrue();
		}
	}
	

	@Given("^click on create new admin$")
	public void clickOnCreateNewAdmin() {
		Global.button.click("");
	}
	
	@When("^enter following values in fields$")
	public void enterFollowingValuesInFields(List<Map<String,String>> enterTextList) {
		for (Map<String, String> objectAndText : enterTextList) {
			String uiObjectName = objectAndText.get("Field");
			String uiElementType = uiObjectName.substring(0, 2);
			switch(uiElementType) {
			case "txt":
				Global.inputfield.setPassword(uiObjectName, objectAndText.get("Value"));
				break;
			case "drp":
				Global.dropdown.selectByVisibleText(uiObjectName, objectAndText.get("Value"));
				break;
			}
		}
	}
	
	@Given("^create button is clicked$")
	public void createButtonIsClicked() {
		Global.button.click("btnAdminCreate");
	}


	

}
