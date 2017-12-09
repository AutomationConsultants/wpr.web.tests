package webUtilities;

import java.util.List;

import org.openqa.selenium.WebElement;

import driver.Global;

public class Link {

	public List<WebElement> getAll(String uiObjectName) {
		try {
			return Global.elements.objects(uiObjectName);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void printAll(String uiObjectName) {
		List<WebElement> allLinks = getAll(uiObjectName);
		for (WebElement link : allLinks) {
			System.out.println(link.getText());
		}
	}
	
	public WebElement get(String uiObjectName) {
		try {
			return Global.elements.object(uiObjectName);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void click(String uiObjectName) {
		try {
			Global.elements.object(uiObjectName).click();
		} catch (Exception e) {
			
		}
	}

}
