package steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.Browser;
import driver.Global;
import excelUtilities.ExcelReader;
import impl.LandingPage;
import impl.Login;
import runners.Hooks;  

public class LandingPageSteps {
	Hooks hooks = new Hooks();
	Login login = new Login();
	LandingPage landingPage = new LandingPage();
	private static final Logger logger = LogManager.getRootLogger();
	Browser browser = new Browser();
	
	@Before
	public void setup() {
		browser.initiate("chrome");
		browser.setup();
		new Global();
		new ExcelReader().loadObjectsFromExcel(new File(System.getProperty("user.dir") + "/src/test/resources/orproperties.xlsx/"));
		logger.info("Browser initiated");
	}
	
	@After
	public void tearDown() {
		if(Global.driver != null) {
			Global.driver.close();
			Global.driver.quit();
			logger.info("Browser closed");
		}
		
	}
	

	
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
	
	@Then("^validate that text \"([^\"]*)\" is displayed on the page$")
	public void validateThatTextIsDisplayed(String textToVerify) {
		assertThat(landingPage.verifyTextPresent(textToVerify)).as("Failed to find:" + textToVerify).isTrue();
	}
	
	@Then("^validate that texts are \"([^\"]*)\" displayed on the page$")
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
		assertThat(Global.elements.object("tblPagination").isDisplayed()).as("Pagination is not displayed").isTrue();
	}
	
	@When("^right nav button is clicked$")
	public void rightNavButtonIssClicked() {
	    Global.elements.object("btnRightNav");
	}

	@Then("^validate that the right nav has accounts$")
	public void validateThatTheRightNavHasAccounts() {
	    if(landingPage.isRightNavOpen()) {
	    		List<WebElement> rightNavAccList = Global.elements.objects("lstRightnavAcc");
	    		assertThat(rightNavAccList).as("No list in the right nav").isNotNull().size().as("Right nav is blank").isGreaterThan(0);
	    		for (WebElement acc : rightNavAccList) {
					System.out.println(acc.getText());
				}
	    }
	}

}
