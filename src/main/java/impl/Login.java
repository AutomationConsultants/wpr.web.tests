package impl;

import driver.Global;
import static org.assertj.core.api.Assertions.assertThat;  

public class Login {
	
	public void perform() {
		Global.inputfield.setText("username", "");
		Global.inputfield.setPassword("password", "");
		Global.button.click("login");
		assertThat(Global.validate.isElementDisplayed("username")).as("Login Failed").isFalse();
	}

}
