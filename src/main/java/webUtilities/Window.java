package webUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import driver.Global;

public class Window {
	
	private static Logger logger = LogManager.getRootLogger();
	private static String parentWinHandle = null;

	public void switchToChild() {
		parentWinHandle = Global.driver.getWindowHandle();
		for (String winHandle : Global.driver.getWindowHandles()) {
			if (!winHandle.equalsIgnoreCase(parentWinHandle)) {
				Global.driver.switchTo().window(winHandle);
				logger.info("Switched to window " + winHandle);
			}
		}
	}
	
	public void switchToParent() {
		Global.driver.switchTo().window(parentWinHandle);
		logger.info("Switched to window " + parentWinHandle);
	}
	
	public void closeChildAndSwitchToParent() {
		Global.driver.close();
		switchToParent();
	}
	
	public void scrollWithJs(int x, int y) {
		Global.jse.executeScript("window.scrollBy(" + x + "," + y + ")");	
		logger.info("Scrolled to element at x- " + x + " and y-" + y);
	}
}
