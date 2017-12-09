package driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Browser {
	private static final int DEFAULT_DRIVER_TIMEOUT = 30;
	private static final String SAFARI_DRIVER_PROP = "webdriver.safari.driver";
	private static final String IE_DRIVER_PROP = "webdriver.ie.driver";
	private static final String GECKO_DRIVER_PROP = "webdriver.gecko.driver";
	private static final String CHROME_DRIVER_PROP = "webdriver.chrome.driver";
	private static final String BASE_DRIVER_PATH = System.getProperty("user.dir") + "/src/main/resources/browserDrivers/";
	private static final String IE_DRIVER_PATH = BASE_DRIVER_PATH + "";
	private static final String SAFARI_DRIVER_PATH = BASE_DRIVER_PATH + "";
	private static final String FIREFOX_DRIVER_PATH = BASE_DRIVER_PATH + "geckodriver.exe";
	private static final String CHROME_DRIVER_PATH = BASE_DRIVER_PATH + "chromedriver";

	public void initiate(String browser) {
		WebDriver driver = null;
		switch(browser.trim().toLowerCase()) {
		case "chrome":
			driver = chrome();
			break;
		case "firefox":
			driver = firefox();
			break;
		case "ie11":
			driver = ie11();
			break;
		case "safari":
			driver = safari();
			break;
		case "chrome-profile":
			driver = chromeProfile();
			break;
		case "firefox-profile":
			driver = firefoxProfile();
			break;
		case "ie11-profile":
			driver = ie11Profile();
			break;
		case "safari-profile":
			driver = safariProfile();
			break;
		default:
			
		}
		Global.driver = driver;
	}
	
	public void setup() {
		Global.driver.manage().timeouts().implicitlyWait(DEFAULT_DRIVER_TIMEOUT, TimeUnit.SECONDS); 
		Global.driver.manage().deleteAllCookies(); 
		Global.driver.manage().window().maximize();
		Global.driver.get("http://wprdev.azurewebsites.net/group/4/dashboard");
	}
	
	private WebDriver chrome() {
		System.setProperty(CHROME_DRIVER_PROP, CHROME_DRIVER_PATH);
		return new ChromeDriver();
	}
	
	private WebDriver firefox() {
		System.setProperty(GECKO_DRIVER_PROP, FIREFOX_DRIVER_PATH);
		return new FirefoxDriver();
	}
	
	private WebDriver ie11() {
		System.setProperty(IE_DRIVER_PROP, IE_DRIVER_PATH);
		return new InternetExplorerDriver();
	}
	
	private WebDriver safari() {
		System.setProperty(SAFARI_DRIVER_PROP, SAFARI_DRIVER_PATH);
		return new SafariDriver();
	}
	
	private WebDriver chromeProfile() {
		System.setProperty(CHROME_DRIVER_PROP, CHROME_DRIVER_PATH);
		return new ChromeDriver();
	}
	
	private WebDriver firefoxProfile() {
		System.setProperty(GECKO_DRIVER_PROP, FIREFOX_DRIVER_PATH);
		return new FirefoxDriver();
	}
	
	private WebDriver ie11Profile() {
		System.setProperty(IE_DRIVER_PROP, IE_DRIVER_PATH);
		return new InternetExplorerDriver();
	}
	
	private WebDriver safariProfile() {
		System.setProperty(SAFARI_DRIVER_PROP, SAFARI_DRIVER_PATH);
		return new SafariDriver();
	}

}
