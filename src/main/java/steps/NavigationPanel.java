package steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberReports.CucumberReporter;
import driver.Browser;
import driver.Global;
import fileUtilities.PropertiesLoader;
import impl.LandingPage;
import impl.Login;  

public class NavigationPanel {
	LandingPage landingPage = new LandingPage();
	private static final Logger logger = LogManager.getLogger(NavigationPanel.class);
	Browser browser = new Browser();
	Login login = new Login();
	CucumberReporter cucumberReporter = new CucumberReporter();
	
	@Before
	public void setup() {
		new Global();
		new PropertiesLoader().loadProps();
		browser.initiate(Global.testProps.getProperty("browser"));
		logger.info("Browser initiated");
		new PropertiesLoader().loadObjects();
		browser.setup();
		Global.jse = (JavascriptExecutor) Global.driver;
//		new ExcelReader().loadObjectsFromExcel(new File(System.getProperty("user.dir") + "/src/test/resources/orproperties.xlsx/"));
		
	}
	
	@After
	public void tearDown() {
		Global.screenshot.take();
		if(Global.driver != null) {
			Global.driver.close();
			Global.driver.quit();
			logger.info("Browser closed");
		}
		cucumberReporter.generateReport(new File(System.getProperty("user.dir")  + "/target/results").getAbsolutePath());
	}
	
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
