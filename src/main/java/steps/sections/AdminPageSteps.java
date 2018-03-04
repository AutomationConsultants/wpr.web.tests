package steps.sections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.Global;
import steps.NavigationPanel;
import steps.ValidateCommonSteps;
import webUtilities.Wait;

public class AdminPageSteps {
	
	private static final Logger logger = LogManager.getLogger(AdminPageSteps.class);;
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
			String uiElementType = uiObjectName.substring(0, 3);
			switch(uiElementType) {
			case "txt":
				Global.inputfield.setText(uiObjectName, objectAndText.get("Value"));
				break;
			case "drp":
				Global.button.click(uiObjectName);
				List<WebElement> drpOptionsList = Global.elements.objects("drpAdminRoleList");
				for (WebElement drpOption : drpOptionsList) {
					if(StringUtils.equals(objectAndText.get("Value"), drpOption.getText())) {
						drpOption.click();
						break;
					}
				}
				break;
			}
		}
	}
	
	@Given("^admin with name \"([^\"]*)\" and email id \"([^\"]*)\" is selected$")
	public void adminWithNameAndEmailIdIsSelected(String adminName, String adminEmail) throws InterruptedException {
		Global.inputfield.setText("txtAdminFullName", adminName);
		Global.elements.object("txtAdminFullName").sendKeys(Keys.ENTER);
		Global.inputfield.setText("txtAdminEmail", adminEmail);
		Global.elements.object("txtAdminEmail").sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		Global.button.click(Global.elements.returnElementXpath("btnAdminSelectToAdd").replace("$$adminFullName$$", adminName));
		if(Global.validate.isElementEnabled("btnAdminAdd")) {
			Global.button.click("btnAdminAdd");
		} else {
			logger.info("Add button not enabled");
		}
	}
	
	@Then("^validate that there are no error messages$")
	public void validateThatThereAreNoErrorMessages() {
		String addAdminError = null;
		if(Global.validate.isTextPresentOnPage("No records found.")) {
			addAdminError = "No records found."; 
			assertThat(addAdminError).as("No records found.").isBlank();
		} else {
			addAdminError = Global.inputfield.getText("lblAdminModalMsg");
			assertThat(addAdminError).as("Failed to add admin. Error: " + addAdminError).isBlank();
		}
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
		new Wait().forAngularLoad();
		validateSectionsDisplayed("Administrator Information");
		validateSectionsDisplayed("Notes");
		validateSectionsDisplayed("Groups");
	}
	
	private void validateSectionsDisplayed(String sectionName ) {
		validateSteps.assertTextPresentOnPage(sectionName);
		assertThat(Global.validate.isElementDisplayedX(Global.elements.returnElementXpath("lblAdminDetailsSection").replace("$$sectionName$$", sectionName))).as(sectionName + " is not displayed").isTrue();
	}

	@Then("^validate that the following error messages are displayed when mandatory field is left blank \"([^\"]*)\"$")
	public void validateThatTheFollowingErrorMessagesAreDisplayedWhileCreatingANewAdmin(String errorListStr) {
		List<String> expErrorList = Arrays.asList(StringUtils.split(errorListStr, ','));
		List<String> actualErrorListStr = new ArrayList<>();
		List<WebElement> actualErrorsList = Global.elements.objects("lblMandatoryFieldError");
		for (WebElement error : actualErrorsList) {
			actualErrorListStr.add(error.getText());
		}
		assertThat(actualErrorListStr).as("All errors not displayed. Displayed errors " + actualErrorListStr).containsAll(expErrorList);
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
		String actualMsg1 = null;
		String actualMsg2 = null;
		String actualMsg3 = null;
		if(Global.validate.isElementDisplayed("lblAdminModalMsg")) {
			actualMsg1 = Global.inputfield.getText("lblAdminModalMsg");
		} 
		if(Global.validate.isElementDisplayed("lblAdminModaCreateDeleteSuccess")) {
			actualMsg2 = Global.inputfield.getText("lblAdminModaCreateDeleteSuccess");
		} 
		if(Global.validate.isElementDisplayed("lblAdminModalPwdResetSuccess")) {
			actualMsg3 = Global.inputfield.getText("lblAdminModalPwdResetSuccess");
		} 
		if(StringUtils.equalsAny(deleteWhitespaces(expMsg), deleteWhitespaces(actualMsg1), deleteWhitespaces(actualMsg2), deleteWhitespaces(actualMsg3))) {
			logger.info("Correct validation message is displayed: " + expMsg);
		} else {
			logger.info("Message 1: " + actualMsg1);
			logger.info("Message 2: " + actualMsg2);
			logger.info("Message 3: " + actualMsg3);
			assertThat(true).as("Actual message is differnt from expected. Actual messages: " + actualMsg1 + ", " + actualMsg2 + ", " + actualMsg3).isFalse();
		}
		
	}
	
	private String deleteWhitespaces(String str) {
		return StringUtils.deleteWhitespace(str);
	}
	
	@Given("^validate that the dropdown \"([^\"]*)\" has following values$")
	public void validateThatTheDropdownHasFollowingValues(String dropdownObj, List<String> dropdownValuesList) {
		List<String> actualValuesList = new ArrayList<>();
		Global.button.click(dropdownObj);
		List<WebElement> elementList = Global.elements.objects("drpAdminRoleList");
		for (WebElement webElement : elementList) {
			actualValuesList.add(webElement.getText());
		}
		assertThat(actualValuesList).as(dropdownObj + " has values: " + actualValuesList).containsAll(dropdownValuesList);
	}
}
