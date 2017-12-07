package runners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	
	private static Logger logger = LogManager.getRootLogger();
	
	
	
	
	

	  private static final Logger LOGGER = LogManager.getLogger(Test.class.getName());
	     
		public static void main(String[] args) {
			test();
			
		}

		private static void renameFiles() {
			
//			"/Users/aishvaryakapoor/Documents/Music copy/English/"
//			"/Users/aishvaryakapoor/Documents/Music copy/Hindi slow/"
//			"/Users/aishvaryakapoor/Documents/Music copy/Oldies/"
//			"/Users/aishvaryakapoor/Documents/Music copy/Sufi/"
			File dir = new File("/Users/aishvaryakapoor/Documents/Music copy/Sufi/");
			File[] allFiles = dir.listFiles();
			String parent = "/Users/aishvaryakapoor/Documents/Music copy1/Sufi/";
			for (File file : allFiles) {
				String path = file.getAbsolutePath();
 				String name = file.getName();
 				if(FilenameUtils.getExtension(name).equals("mp3")) {
					name = name.replace(".mp3", "").replaceAll("_", "").replaceAll("[^a-zA-Z ]+", "").trim() + ".mp3";
					try {
						FileUtils.copyFile(file, new File(parent+name));
						System.out.println(parent+name);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 				}
				
			}
		}

		private static void test() {
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.chrome.driver", "/Users/aishvaryakapoor/Downloads/selenium/chromedriver");
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.get("http://wprdev.azurewebsites.net/account/login");
			driver.findElement(By.id("LoginUserName")).sendKeys("Jerri Woodard");;
			driver.findElement(By.id("LoginPassword")).sendKeys("Winter24");
			driver.findElement(By.xpath("//button[\"@type='submit'\"]")).click();
			//driver.findElement(By.xpath(".//*[@class='sidebar-toggler pull-right visible-md visible-lg']")).click();
			driver.findElement(By.xpath("//a//span[text()='Pet Owners']")).click();
			
			
		}
	    

		System.setProperty("webdriver.chrome.driver", "/Users/aishvaryakapoor/Downloads/selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
	}
}
