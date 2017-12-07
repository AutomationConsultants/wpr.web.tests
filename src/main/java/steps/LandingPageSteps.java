package steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.apache.tools.ant.util.CollectionUtils;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.Global;
import impl.LandingPage;
import impl.Login;  

public class LandingPageSteps {
	Login login = new Login();
	LandingPage landingPage = new LandingPage();
	
	@Given("^login is completed on world pet registry website$")
	public void loginIsCompletedOnWorldPetRegistryWebsite() {
		login.perform();
	}

	@Then("^validate that the left navigation is open$")
	public void validateThatTheLeftNavigationIsOpen() {
		landingPage.isLeftNavOpen();
	}

	@Then("^validate that the following text is displayed on the page$")
	public void validateThatTextsAreDisplayed(List<String> textToVerifyList) {
		for (String textToVerify : textToVerifyList) {
			assertThat(landingPage.verifyTextPresent(textToVerify)).as("Failed to find:" + textToVerify).isTrue();
		}
		
	}
	
	@Then("^validate that text \"([^\"]*)\" is displayed on the the page$")
	public void validateThatTextIsDisplayed(String textToVerify) {
		assertThat(landingPage.verifyTextPresent(textToVerify)).as("Failed to find:" + textToVerify).isTrue();
	}
	
	@Then("^validate that texts \"([^\"]*)\" are displayed on the the page$")
	public void validateThatTextsAreDisplayed(String strTextToVerifyList) {
		List<String> textToVerifyList = Arrays.asList(StringUtils.split(strTextToVerifyList, ","));
		validateThatTextsAreDisplayed(textToVerifyList);
	}

	@Then("^validate that options are displayed in the left navigation panel$")
	public void validateThatOptionsAreDisplayedInTheLeftNavigationPanel(List<String> leftNavOptions) {
		for (String optionName : leftNavOptions) {
			landingPage.validateOptionOnLeftNav(optionName);
		}
	}
	
	@When("^\"([^\"]*)\" link is clicked on the left navigation$")
	public void linkIsClickedOnTheLeftNavigation(String linkName) {
	    Global.elements.findByTextOnPage(linkName).click();
	}

	@Then("^validate that the following fields are displayed \"([^\"]*)\" on the page$")
	public void validateThatFollowingFieldsAreDisplayed(String strFieldList) {
		List<String> fieldList = Arrays.asList(StringUtils.split(strFieldList, ","));
		validateThatFollowingFieldsAreDisplayed(fieldList);
	}
	
	@Then("^validate that following fields are displayed on the page$")
	public void validateThatFollowingFieldsAreDisplayed(List<String> fieldList) {
		for (String uiObjectName : fieldList) {
			Global.validate.isElementDisplayedImmediately(uiObjectName);
		}
	}
	
	@Then("^validate that there is data in the table$")
	public void validateThatThereIsDataInTheTable() {
		List<WebElement> rowList = Global.elements.objects("tblTable");
		assertThat(rowList).isNotNull().size().isGreaterThan(0);
	}

	@When("^validate the pagination at the bottom is displayed$")
	public void validateThePaginationAtTheBottom() {
		assertThat(Global.elements.object("tblPagination")).isNotNull();
		assertThat(Global.elements.object("tblPagination").isDisplayed()).isTrue();
	}

}
