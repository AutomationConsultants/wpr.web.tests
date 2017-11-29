package webUtilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.Global;

public class Wait {
	Elements common = new Elements();

	public void forPageToLoad() {
		new WebDriverWait(Global.driver, 30).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public WebElement fluentForElement(String uiObjectName) {
		try {
			 return fluentWait(30).until(ExpectedConditions.visibilityOf(common.object(uiObjectName)));
		} catch (Exception e) {
			return null;
		}
	}

	private FluentWait<WebDriver> fluentWait(int seconds) {
		return new FluentWait<>(Global.driver).withTimeout(seconds, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class, TimeoutException.class)
				.ignoring(StaleElementReferenceException.class);
	}

	public WebElement fluentForElementWithoutWait(String uiObjectName) {
		try {
			return fluentWait(0).until(ExpectedConditions.visibilityOf(common.object(uiObjectName)));
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement fluentForElementForSecs(String uiObjectName, int seconds) {
		try {
			return fluentWait(seconds).until(ExpectedConditions.visibilityOf(common.object(uiObjectName)));
		} catch (Exception e) {
			return null;
		}
	}

}
