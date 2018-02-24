package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"features/Test.feature"}, 
glue = {"steps"}, 
plugin = {"pretty", "json:target/results/cucumber.json"}, 
tags = {"@test"},
snippets = SnippetType.CAMELCASE)

public class CukeRunner {
	
	public CukeRunner() {
		System.out.println(getClass().getClassLoader().getResource("logging.properties"));
	}
	

}
