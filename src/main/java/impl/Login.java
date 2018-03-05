package impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import driver.Global;  

public class Login {
	private static final Logger logger = LogManager.getLogger(Login.class);
	
	public boolean perform() {
		String username  = "Jerri Woodard";
		String password = "Winter24";
		Global.inputfield.setText("txtUsername", username);
		Global.inputfield.setPassword("txtPassword", password);
		Global.button.click("btnLogin");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Global.validate.isElementDisplayedInSecs("txtUsername", 10);
	}

}
