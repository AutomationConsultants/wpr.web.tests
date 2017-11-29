package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/main/resources/features/Example.feature"}, glue = { "src/main/java/steps" }, plugin = {"pretty", "html:target/cucumber"}, tags = { "~@wip" })

public class CukeRunner {

}
