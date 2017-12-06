package steps;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import impl.LandingPage;
import impl.Login;

import static org.assertj.core.api.Assertions.assertThat;  

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

	@Then("^validate that text is displayed on the landing page$")
	public void validateThatTextIsDisplayed(List<String> textToVerifyList) {
		for (String textToVerify : textToVerifyList) {
			assertThat(landingPage.verifyTextPresent(textToVerify)).as("Failed to find:" + textToVerify).isTrue();
		}
		
	}

	@Then("^validate that options are displayed in the left navigation panel$")
	public void validateThatOptionsAreDisplayedInTheLeftNavigationPanel(List<String> leftNavOptions) {
		for (String optionName : leftNavOptions) {
			landingPage.validateOptionOnLeftNav(optionName);
		}
	}

}
