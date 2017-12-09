package webUtilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.Global;

public class Wait {

	public void forPageToLoad() {
		new WebDriverWait(Global.driver, 30).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public WebElement fluentForElement(String uiObjectName) {
		try {
			 return fluentWait(30).until(ExpectedConditions.visibilityOf(Global.elements.object(uiObjectName)));
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
			return fluentWait(0).until(ExpectedConditions.visibilityOf(Global.elements.object(uiObjectName)));
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement fluentForElementForSecs(String uiObjectName, int seconds) {
		try {
			return fluentWait(seconds).until(ExpectedConditions.visibilityOf(Global.elements.object(uiObjectName)));
		} catch (Exception e) {
			return null;
		}
	}
	
    public void forAngularLoad() {
        WebDriverWait wait = new WebDriverWait(Global.driver,15);
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());
        //Get Angular is Ready
        boolean angularReady = Boolean.parseBoolean(Global.jse.executeScript(angularReadyScript).toString());
        //Wait ANGULAR until it is Ready!
        if(!angularReady) {
            System.out.println("ANGULAR is NOT Ready!");
            //Wait for Angular to load
            wait.until(angularLoad);
        } else {
            System.out.println("ANGULAR is Ready!");
        }
    }

}
