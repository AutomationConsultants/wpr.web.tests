package webUtilities;

import java.util.List;

import org.openqa.selenium.WebElement;

public class Link {
	Elements common = new Elements();

	public List<WebElement> getAll(String uiObjectName) {
		try {
			return common.objects(uiObjectName);
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
			return common.object(uiObjectName);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void click(String uiObjectName) {
		try {
			common.object(uiObjectName).click();
		} catch (Exception e) {
			
		}
	}

}
