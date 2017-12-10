package webUtilities;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import driver.Global;

public class Link {
	
	private static Logger logger = LogManager.getRootLogger();

	public List<WebElement> getAll(String uiObjectName) {
		try {
			List<WebElement> allLinks = Global.elements.objects(uiObjectName);
			logger.info("All links: " + allLinks);
			return allLinks;
		} catch (Exception e) {
			logger.info(e);
			return null;
		}
	}
	
	public void printAll(String uiObjectName) {
		List<WebElement> allLinks = getAll(uiObjectName);
		for (WebElement link : allLinks) {
			logger.info(link.getText());
		}
	}
	
	public WebElement get(String uiObjectName) {
		try {
			WebElement link = Global.elements.object(uiObjectName);
			logger.info(uiObjectName + " has link " + link);
			return link;
		} catch (Exception e) {
			logger.info(e);
			return null;
		}
	}
	
	public void click(String uiObjectName) {
		try {
			Global.elements.object(uiObjectName).click();
			logger.info(uiObjectName + " link is clicked ssss");
		} catch (Exception e) {
			logger.info(e);
		}
	}

}
