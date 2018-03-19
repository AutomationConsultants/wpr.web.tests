package runners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import cucumberReports.CucumberReporter;

public class CukeRunner {
	private static final Logger logger = LogManager.getLogger(CukeRunner.class);
	private final String CUCUMBER_PROPERTY_FILE_PATH = "/src/main/resources/PropertyFiles/cucumber.properties"; 


	@Test
	public void test() {
		logger.info("Starting test execution..");
		setCucumberOptions();
		JUnitCore jUnitCore = new JUnitCore();
		Result result = jUnitCore.run(CucumberRunner.class);
		logger.info("Cuke Runner Test" + result.toString());
		logger.info("Test execution completed. Failures: " + result.getFailureCount());
	}

	@AfterClass
	public static void after() {
		new CucumberReporter().generateReport();
	}
	
	private void setCucumberOptions() {
		try {
			String cukeOptions = "features";
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")  + CUCUMBER_PROPERTY_FILE_PATH));
			Properties cucumberProperties = new Properties();
			cucumberProperties.load(file);
			if(StringUtils.isNotEmpty(cucumberProperties.getProperty("features"))) {
				cukeOptions = cukeOptions + "/" + cucumberProperties.getProperty("features");
			}
			if(StringUtils.isNotEmpty(cucumberProperties.getProperty("tags"))) {
				cukeOptions = cukeOptions + " --tags " + cucumberProperties.getProperty("tags");
			}
			System.setProperty("cucumber.options", cukeOptions);
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
