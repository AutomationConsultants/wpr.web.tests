package webUtilities;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import driver.Global;


public class Elements {

	public List<WebElement> returnWebElements(String locType, String locator) {
		return findElements(byLocator(locType, locator));
	}

	public By byLocator(String locType, String locator) {
		By by = null;
		switch (StringUtils.lowerCase(locType.trim())) {
		case "id":
			by = By.id(locator);
			break;
		case "tagName":
			by = By.tagName(locator);
			break;
		case "xpath":
			by = By.xpath(locator);
			break;
		case "cssSelector":
			by = By.cssSelector(locator);
			break;
		case "className":
			by = By.className(locator);
			break;
		case "name":
			by = By.name(locator);
			break;
		case "linkText":
			by = By.linkText(locator);
			break;
		case "partialLinkText":
			by = By.partialLinkText(locator);
			break;
		default:
		}
		return by;
	}

	public List<WebElement> findElements(By by) {
		List<WebElement> elements = null;
		try {
			elements = Global.driver.findElements(by);
		} catch (NoSuchElementException e) {

		} catch (Exception e) {

		}
		return elements;
	}

	public WebElement findElement(By by) {
		WebElement element = null;
		try {
			element = Global.driver.findElement(by);
		} catch (NoSuchElementException e) {

		} catch (Exception e) {

		}
		return element;
	}

	public WebElement object(String uiObjectName) {
		return findElement(Global.locatorProps.get(uiObjectName));
	}

	public List<WebElement> objects(String uiObjectName) {
		return findElements(Global.locatorProps.get(uiObjectName));
	}

}
