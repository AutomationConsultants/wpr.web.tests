package runners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import cucumberReports.CucumberReporter;

public class CukeRunner {
	private static final Logger logger = LogManager.getLogger(CukeRunner.class);

	@Test
	public void test() {
		logger.info("Starting test execution..");
		JUnitCore jUnitCore = new JUnitCore();
		Result result = jUnitCore.run(CucumberRunner.class);
		logger.info("Cuke Runner Test" + result.toString());
		logger.info("Test execution completed. Failures: " + result.getFailureCount());
	}

	@AfterClass
	public static void after() {
		new CucumberReporter().generateReport();
	}
}
