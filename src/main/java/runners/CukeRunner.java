package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"features"}, 
glue = {"steps"}, 
plugin = {"pretty", "json:results/cucumber.json"}, 
tags = {"~@wip","@subGroups"},
snippets = SnippetType.CAMELCASE)

public class CukeRunner {
	

}
