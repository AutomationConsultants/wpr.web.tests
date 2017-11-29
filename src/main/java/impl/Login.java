package impl;

import driver.Global;

public class Login {
	
	public void login() {
		Global.inputfield.setText("username", "");
		Global.inputfield.setPassword("password", "");
		Global.button.click("login");
	}

}
