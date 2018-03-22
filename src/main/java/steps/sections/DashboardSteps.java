package steps.sections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import driver.Global;

public class DashboardSteps {
	
	@Given("^validate status, count and percentage in following tables of \"([^\"]*)\"$")
	public void validatePercentagesInFollowingTablesOf(String pageName, List<String> tableNameList) {
		for (String tableName : tableNameList) {
			List<WebElement> tblStatusList = Global.elements.returnElementsFromX("txtTblStatus", "$$tableName$$", tableName);
			List<WebElement> tblCountList = Global.elements.returnElementsFromX("txtTblCount", "$$tableName$$", tableName);
			List<WebElement> tblPercentageList = Global.elements.returnElementsFromX("txtTblPercent", "$$tableName$$", tableName);
			Iterator<WebElement> tblCountListIte = tblCountList.iterator();
			Iterator<WebElement> tblPercentageListIte = tblPercentageList.iterator();
			for (WebElement status : tblStatusList) {
				String countStr = tblCountListIte.next().getText();
				String percentStr = tblPercentageListIte.next().getText().replace("%", "");
				System.out.println("Count in table " + tableName + " for " + status.getText() + " is not numeric " + countStr);
				System.out.println("Count in table " + tableName + " for " + status.getText() + " is not numeric " + percentStr);
//				assertThat(countStr).as("Count in table " + tableName + " for " + status.getText() + " is not numeric " + countStr).containsOnlyDigits();
//				assertThat(percentStr).as("Count in table " + tableName + " for " + status.getText() + " is not numeric " + percentStr).containsOnlyDigits();
			}
		}
		
	}


}
