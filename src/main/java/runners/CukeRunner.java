package runners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CukeRunner  extends CucumberRunner {
	private static final Logger logger = LogManager.getLogger(CukeRunner.class);
	
	@Before
	public void setup() {
		logger.info("Cuke Runner Before");
	}
	public CukeRunner() {
		logger.info("Cuke Runner Constructor");
	}
	
	@Test
	public void test() {
		logger.info("Cuke Runner Test");
	}
	
	@After
	public void after() {
		logger.info("Cuke Runner After");
	}
}
