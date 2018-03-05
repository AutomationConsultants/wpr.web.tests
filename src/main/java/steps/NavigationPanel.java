package steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.Global;
import impl.LandingPage;
import impl.Login;  

public class NavigationPanel {
	LandingPage landingPage = new LandingPage();
	private static final Logger logger = LogManager.getLogger(NavigationPanel.class);
	Login login = new Login();
	CucumberHooks hooks = new CucumberHooks();

	@Given("^login is completed on world pet registry website$")
	public void loginIsCompletedOnWorldPetRegistryWebsite() {
		assertThat(login.perform()).as("Login Failed").isFalse();
		logger.info("Login Successful!");
	}

	@Then("^validate that the left navigation is open$")
	public void validateThatTheLeftNavigationIsOpen() {
		assertThat(landingPage.isLeftNavOpen()).as("Left Nav not open").isTrue();
	}

	@Then("^validate that options are displayed in the left navigation panel$")
	public void validateThatOptionsAreDisplayedInTheLeftNavigationPanel(List<String> leftNavOptions) {
		for (String optionName : leftNavOptions) {
			assertThat(landingPage.validateOptionOnLeftNav(optionName)).as("Left nav is not open").isTrue();
		}
	}
	
	@When("^\"([^\"]*)\" link is clicked on the left navigation$")
	public void linkIsClickedOnTheLeftNavigation(String linkName) {
	    Global.elements.findByTextOnPage(linkName).click();
	}

	@When("^right nav is opened$")
	public void rightNavIsOpened() {
		landingPage.openRightNavIfClosed();
	}
	
	@When("^right nav is closed$")
	public void rightNavIsClosed() {
		landingPage.closeRightNavIfOpen();
	}

	@Then("^validate that the right nav has accounts$")
	public void validateThatTheRightNavHasAccounts() {
    		List<WebElement> rightNavAccList = Global.elements.objects("lstRightnavAcc");
    		if(CollectionUtils.isNotEmpty(rightNavAccList) && rightNavAccList.size() == 1) {
    			Global.button.click(Global.elements.returnElementFromX("lnkRightNavLabName", "$$labName$$", rightNavAccList.get(0).getText()));
    			rightNavAccList = Global.elements.objects("lstRightnavAcc");
    		}
    		assertThat(rightNavAccList).as("No list in the right nav").isNotNull().size().as("Right nav is blank").isGreaterThan(0);
		for (WebElement acc : rightNavAccList) {
			logger.info(acc.getText());
		}
	}
	
	@Then("^validate that the correct dashboard page is opened for each account in right nav$")
	public void validateThatTheCorrectDashboardPageIsOpenedForEachAccount() {
		List<WebElement> rightNavAccList = Global.elements.objects("lstRightnavAcc");
		assertThat(rightNavAccList).as("No list in the right nav").isNotNull().size().as("Right nav is blank").isGreaterThan(0);
		for (WebElement acc : rightNavAccList) {
			acc.click();
			String accountText = acc.getText();
			String header = Global.elements.object("lblHeader").getText();
			assertThat(header).as("Clicked on:" + accountText + " but header is: " + header).isEqualTo(accountText);
		}
	}
}
