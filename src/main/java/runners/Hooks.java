package runners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import driver.Browser;
import driver.Global;

public class Hooks {
	
	private static final Logger logger = LogManager.getRootLogger();
	
	Browser browser = new Browser();
	
	@Before
	public void setup() {
		browser.initiate("chrome");
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
	

}
