package impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import driver.Global;  

public class Login {
	private static final Logger logger = LogManager.getLogger(Login.class);
	
	public boolean perform() {
		String username  = Global.testProps.getProperty("username");
		String password = Global.testProps.getProperty("password");
		Global.wait.forPageToLoad();
		Global.inputfield.setText("txtUsername", username);
		Global.inputfield.setPassword("txtPassword", password);
		Global.button.click("btnLogin");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		return Global.validate.isElementDisplayedInSecs("txtUsername", 10);
	}

}
