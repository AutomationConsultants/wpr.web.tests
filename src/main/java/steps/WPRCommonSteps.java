package steps;

import cucumber.api.java.en.Then;
import driver.Global;

import static org.assertj.core.api.Assertions.assertThat;

public class WPRCommonSteps {
	
	@Then("^validate that the header is \"([^\"]*)\"$")
	public void validateThatTheHeaderIs(String expHeader) {
		assertThat(Global.validate.textOfInput("lblModalHeader", expHeader)).as("Actual header differs from expected").isTrue();
	}

}
