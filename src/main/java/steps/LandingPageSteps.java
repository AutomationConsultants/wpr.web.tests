package steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.Global;

public class LandingPageSteps {
	
	@Then("^validate that the following text is displayed on the page$")
	public void validateThatTextsAreDisplayed(List<String> textToVerifyList) {
		for (String textToVerify : textToVerifyList) {
			assertThat(Global.validate.verifySimilarTextPresent(textToVerify)).as("Failed to find:" + textToVerify).isTrue();
		}
	}
	
//	@Then("^validate that text \"([^\"]*)\" is displayed on the page$")
//	public void validateThatTextIsDisplayed(String textToVerify) {
//		assertThat(Global.validate.verifyTextPresent(textToVerify)).as("Failed to find:" + textToVerify).isTrue();
//	}
//	
//	@Then("^validate that texts are \"([^\"]*)\" displayed on the page$")
//	public void validateThatTextsAreDisplayed(String strTextToVerifyList) {
//		List<String> textToVerifyList = Arrays.asList(StringUtils.split(strTextToVerifyList, ","));
//		validateThatTextsAreDisplayed(textToVerifyList);
//	}
	
//	@Then("^validate that the following fields are displayed \"([^\"]*)\" on the page$")
//	public void validateThatFollowingFieldsAreDisplayed(String strFieldList) {
//		List<String> fieldList = Arrays.asList(StringUtils.split(strFieldList, ","));
//		validateThatFollowingFieldsAreDisplayed(fieldList);
//	}
	
	@Then("^validate that following fields are displayed on the page$")
	public void validateThatFollowingFieldsAreDisplayed(List<String> fieldList) {
		for (String uiObjectName : fieldList) {
			assertThat(Global.validate.isElementDisplayedImmediately(uiObjectName)).as(uiObjectName + " is not displayed").isTrue();
		}
	}
	
	@Then("^validate that following fields are disabled on the page$")
	public void validateThatFollowingFieldsAreDisabled(List<String> fieldList) {
		for (String uiObjectName : fieldList) {
			assertThat(Global.validate.isElementEnabled(uiObjectName)).as(uiObjectName + " is enabled. Should be disabled").isFalse();
		}
	}
	
	@When("^following mandatory fields are left blank$")
	public void followingMandatoryFieldsAreLeftBlank(List<String> fieldList) {
		for (String uiObjectName : fieldList) {
			Global.elements.object(uiObjectName).clear();
			Global.elements.object(uiObjectName).sendKeys(Keys.ENTER);
		}
	}
	
	@Then("^validate that there is data in the table$")
	public void validateThatThereIsDataInTheTable() {
		List<WebElement> rowList = Global.elements.objects("tblTable");
		assertThat(rowList).as(rowList + " Table is missing").isNotNull().size().as("Number of rows in the table: " + rowList.size()).isGreaterThan(0);
		System.out.println("Number of rows: "+ rowList.size());
		System.out.println("Row data: " + rowList.get(0).toString());
	}

	@When("^validate the pagination at the bottom is displayed$")
	public void validateThePaginationAtTheBottom() {
		assertThat(Global.elements.object("tblPagination")).as("Pagination is missing").isNotNull();
		assertThat(Global.elements.object("tblPagination").isDisplayed()).as("Pagination is not displayed").isTrue();
	}
}
