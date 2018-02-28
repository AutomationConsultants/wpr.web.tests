package steps.sections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.Global;
import steps.NavigationPanel;
import steps.ValidateCommonSteps;

public class AdminPageSteps {
	
	NavigationPanel navigationPanel = new NavigationPanel();
	ValidateCommonSteps validateSteps = new ValidateCommonSteps();
	
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
	
	@Then("^validate that there are no error messages$")
	public void validateThatThereAreNoErrorMessages() {
		String addAdminError = Global.inputfield.getText("lblAdminModalMsg");
		assertThat(addAdminError).as("Failed to add admin. Error: " + addAdminError).isBlank();
	}
	
	@Then("^validate admin details page$")
	public void validateAdminDetailsPage() {
		List<WebElement> adminList = Global.elements.objects("lblAdminNameList");
		if(CollectionUtils.isNotEmpty(adminList)) {
			for (WebElement adminName : adminList) {
				openAdminDetailsPageAndValidate(adminName);
			}
		} else {
			System.out.println("Could not find any admin with selected criteria");
		}
	}
	
	@When("^admin details page is opened for the admin$")
	public void adminDetailsPageIsOpenedForTheAdmin() {
		List<WebElement> adminList = Global.elements.objects("lblAdminNameList");
		if(CollectionUtils.isNotEmpty(adminList)) {
			openAdminDetailsPageAndValidate(adminList.get(0));
			navigationPanel.linkIsClickedOnTheLeftNavigation("Admins");
		} else {
			System.out.println("Could not find any admin with selected criteria");
		}
	}
	
	@When("^log user and new password$")
	public void logUserAndNewPwd() {
		System.out.println("User: " + Global.inputfield.getText("lblAdminPwdResetUser"));
		System.out.println("New Password: " + Global.inputfield.getText("lblAdminPwdResetNewPwd"));
	}
	
	private void openAdminDetailsPageAndValidate(WebElement adminName) {
		System.out.println("Validating admin details for: " + adminName.getText());
		Global.button.click(adminName);
		validateAdminPage();
	}
	
	private void validateAdminPage() {
//		TODO
		validateSteps.assertTextPresentOnPage("Administrator Information");
		validateSteps.assertTextPresentOnPage("Notes");
		validateSteps.assertTextPresentOnPage("Groups");
		validateSectionsDisplayed("Administrator Information");
		validateSectionsDisplayed("Notes");
		validateSectionsDisplayed("Groups");
	}
	
	private void validateSectionsDisplayed(String sectionName ) {
		assertThat(Global.validate.isElementDisplayed(Global.elements.returnElementXpath("lblAdminDetailsSection").replace("$$sectionName$$", sectionName))).as(sectionName = " is not displayed").isTrue();
	}

	@Then("^validate that the following error messages are displayed when mandatory field is left blank \"([^\"]*)\"$")
	public void validateThatTheFollowingErrorMessagesAreDisplayedWhileCreatingANewAdmin(List<String> errorList) {
//		TODO need to check if the method param comes as a list or single comma separated string
//		List<String> errorList = Arrays.asList(StringUtils.split(errors, ','));
		List<String> actualErrorListStr = new ArrayList<>();
		List<WebElement> actualErrorsList = Global.elements.objects("lblMandatoryFieldError");
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
	
	@When("^admin with name \"([^\"]*)\", role \"([^\"]*)\" and username \"([^\"]*)\" is searched$")
	public void adminWithNameRoleAndUsernameIsSearched(String name, String role, String username) {
		Global.inputfield.setText("txtAdminName", name);
		Global.inputfield.setText("txtAdminRole", role);
		Global.inputfield.setText("txtAdminUserName", username);
		Global.elements.object("txtAdminUserName").sendKeys(Keys.ENTER);
	}
	
	@Then("^validate the message \"([^\"]*)\" is displayed$")
	public void validateTheMessageIsDisplayed(String expMsg) {
//		TODO need to check if isElementDisplayedImmediately or isElementDisplayed should be used
		String actualMsg = null;
		if(Global.validate.isElementDisplayedImmediately("lblAdminModalMsg")) {
			actualMsg = Global.inputfield.getText("lblAdminModalMsg");
		} else if(Global.validate.isElementDisplayedImmediately("lblAdminModalDeleteSuccess")) {
			actualMsg = Global.inputfield.getText("lblAdminModalDeleteSuccess");
		} else if(Global.validate.isElementDisplayedImmediately("lblAdminModalPwdResetSuccess")) {
			actualMsg = Global.inputfield.getText("lblAdminModalPwdResetSuccess");
		} 
		
		assertThat(actualMsg).as("Actual message is differnt from expected. Actual message: " + actualMsg).isEqualToIgnoringWhitespace(expMsg);
	}
}
