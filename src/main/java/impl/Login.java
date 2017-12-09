package impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import driver.Global;  

public class Login {
	private static final Logger logger = LogManager.getRootLogger();
	
	public boolean perform() {
		String username  = "Jerri Woodard";
		String password = "Winter24";
		Global.inputfield.setText("txtUsername", username);
		Global.inputfield.setPassword("txtPassword", password);
		Global.button.click("btnLogin");
		return Global.validate.isElementDisplayed("txtUsername");
	}

}
