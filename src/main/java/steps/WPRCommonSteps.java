package steps;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.en.Then;
import driver.Global;

public class WPRCommonSteps {
	
	@Then("^validate that the header is \"([^\"]*)\"$")
	public void validateThatTheHeaderIs(String expHeader) {
		assertThat(Global.validate.textOfInput("lblModalHeader", expHeader)).as("Actual header differs from expected").isTrue();
	}

}
