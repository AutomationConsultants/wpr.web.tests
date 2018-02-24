package runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import cucumberReports.CucumberReporter;
import driver.Global;
import fileUtilities.PropertiesLoader;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"features"}, 
glue = {"steps"}, 
plugin = {"pretty", "json:target/results/cucumber.json", "junit:target_junit/cucumber.xml", "html:target/results/	"}, 
tags = {"~@wip","@rightNavAll"},
snippets = SnippetType.CAMELCASE)

public class CukeRunner {
@BeforeClass
public static void setup() {
	new Global();
	new PropertiesLoader().loadProps();
}
	
	
@AfterClass
public static void sendReport() {
	CucumberReporter.generateReport();
}
}
