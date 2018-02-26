package steps.sections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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
	
	@Given("^admin with name \"([^\"]*)\" and email id \"([^\"]*)\" is selected$")
	public void adminWithNameAndEmailIdIsSelected(String adminName, String adminEmail) throws InterruptedException {
		Global.inputfield.setText("txtAdminName", adminName);
		Global.elements.object("txtAdminName").sendKeys(Keys.ENTER);
		Global.inputfield.setText("txtAdminEmail", adminEmail);
		Global.elements.object("txtAdminEmail").sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		Global.button.click(Global.elements.returnElementXpath("btnAdminSelectToAdd").replace("$$email$$", adminEmail));
		if(Global.validate.isElementEnabled("btnAdminAdd")) {
			Global.button.click("btnAdminAdd");
		}
	}
	
	@Then("^validate that there are not error messages$")
	public void validateThatThereAreNotErrorMessages() {
		String addAdminError = Global.inputfield.getText("lblAdminAddError");
		assertThat(addAdminError).as("Failed to add admin. Error: " + addAdminError).isBlank();
	}
	
	@Then("^validate admin details page$")
	public void validateAdminDetailsPage() {
		List<WebElement> adminList = Global.elements.objects("lblAdminNameList");
		for (WebElement adminName : adminList) {
			System.out.println("Validating admin details for: " + adminName.getText());
			Global.button.click(adminName);
		}
	}
	
	private void validateAdminPage() {
		
	}
	
	@Then("^validate that the following error messages are displayed while creating a new admin \"([^\"]*)\"$")
	public void validateThatTheFollowingErrorMessagesAreDisplayedWhileCreatingANewAdmin(List<String> errorList) {
//		TODO need to check if the method param comes as a list or single comma separated string
//		List<String> errorList = Arrays.asList(StringUtils.split(errors, ','));
		List<String> actualErrorListStr = new ArrayList<>();
		List<WebElement> actualErrorsList = Global.elements.objects("lblAdminCreateError");
		for (WebElement error : actualErrorsList) {
			actualErrorListStr.add(error.getText());
		}
		assertThat(actualErrorListStr).as("All errors not displayed. Displayed errors " + actualErrorListStr).containsAll(errorList);
	}
	
	@Then("^validate that a list of existing admins is populated$")
	public void validateThatAListOfExistingAdminsIsPopulated() {
		List<WebElement> existingDdminList = Global.elements.objects("lblAdminExistingList");
		assertThat(existingDdminList).as("No list for existing admins").isNotNull().as("No list for existing admins").isNotEmpty();
		
	}
	
}
