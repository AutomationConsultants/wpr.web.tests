package impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seleniumhq.jetty9.util.log.Log;

import driver.Global;  

public class Login {
	private static final Logger logger = LogManager.getLogger(Login.class);
	
	public boolean perform() {
		Log.getLog().info("This is test");
		logger.error(getClass().getClassLoader().getResource("log4j2.properties"));

		String username  = "Jerri Woodard";
		String password = "Winter24";
		Global.inputfield.setText("txtUsername", username);
		Global.inputfield.setPassword("txtPassword", password);
		Global.button.click("btnLogin");
		return Global.validate.isElementDisplayed("txtUsername");
	}

}
