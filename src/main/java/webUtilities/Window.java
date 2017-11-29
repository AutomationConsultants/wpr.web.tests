package webUtilities;

import driver.Global;

public class Window {
	private static String parentWinHandle = null;

	public void switchToChild() {
		parentWinHandle = Global.driver.getWindowHandle();
		for (String winHandle : Global.driver.getWindowHandles()) {
			if (!winHandle.equalsIgnoreCase(parentWinHandle)) {
				Global.driver.switchTo().window(winHandle);
			}
		}
	}
	
	public void switchToParent() {
		Global.driver.switchTo().window(parentWinHandle);
	}
	
	public void closeChildAndSwitchToParent() {
		Global.driver.close();
		Global.driver.switchTo().window(parentWinHandle);
	}
	
	public void scrollWithJs(int x, int y) {
		Global.jse.executeScript("window.scrollBy(" + x + "," + y + ")");	
	}

}
