package webUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import driver.Global;

public class Screenshot {
	
	private static final String SEPARATOR = "/";
	private static Logger logger = LogManager.getRootLogger();
	private static final String SCREENSHOT_OUTPUT = "screenshotOutput";
	private static final String DEFAULT_SCREENSHOT_PATH = System.getProperty("user.dir") + "/target/Automation/Output/Screenshots/";

	public void take(String outputPath) {
		try {
			String scrShtName = Calendar.getInstance().getTime().toGMTString();
			if(StringUtils.isEmpty(outputPath)) {
				outputPath = DEFAULT_SCREENSHOT_PATH + SEPARATOR + scrShtName;
			} else {
				outputPath = outputPath + SEPARATOR + scrShtName;
			}
			File scrDir = new File(outputPath);
			if(!scrDir.isDirectory()) {
				scrDir.mkdir();
			} else {
				deleteDir(scrDir);
				scrDir.mkdir();
			}
			File scrFile = ((TakesScreenshot) Global.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(outputPath));
			logger.info("Screenshot saved at: " + outputPath);
		} catch (IOException e) {
			logger.info("Error while saving screenshot" + e);
		}
	}

	private void deleteDir(File scrDir) {
		try {
			FileUtils.deleteDirectory(scrDir);
		} catch (IOException e) {
			logger.error("Error deleting the directory: " + scrDir.getAbsolutePath());
		} catch (IllegalArgumentException e) {
			logger.error("Directory does not exist - could not delete: " + scrDir.getAbsolutePath());
		}
	}

	public void take() {
		take(System.getProperty(SCREENSHOT_OUTPUT));
	}
	
	public void takeFullscreen() {
//	Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//    ImageIO.write(fpScreenshot.getImage(),"PNG",new File("D:///FullPageScreenshot.png"));
	}
    
    

}
