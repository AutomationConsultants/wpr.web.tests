package impl;

import driver.Global;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;  

public class Login {
	
	public void perform() {
		Global.inputfield.setText("txtUsername", "Jerri Woodard");
		Global.inputfield.setPassword("txtPassword", "Winter24");
		Global.button.click("btnLogin");
		assertThat(Global.validate.isElementDisplayed("txtUsername")).as("Login Failed").isFalse();
	}

}
