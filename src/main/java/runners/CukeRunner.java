package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"features"}, 
glue = {"steps"}, 
plugin = {"pretty", "json:target/results/cucumber.json"}, 
tags = {"~@wip","@rightNavAll"},
snippets = SnippetType.CAMELCASE)

public class CukeRunner {
	

}
