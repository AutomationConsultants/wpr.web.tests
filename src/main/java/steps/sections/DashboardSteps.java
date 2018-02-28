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
		List<WebElement> tblStatusList = Global.elements.objects("txtTblStatus");
		List<WebElement> tblCountList = Global.elements.objects("txtTblCount");
		List<WebElement> tblPercentageList = Global.elements.objects("txtTblPercent");
		Iterator tblCountListIte = tblCountList.iterator();
		Iterator tblPercentageListIte = tblPercentageList.iterator();
		for (String tableName : tableNameList) {
			for (WebElement status : tblStatusList) {
				String countStr = tblCountListIte.next().toString();
				String percentStr = tblPercentageListIte.next().toString().replace("%", "");
				assertThat(countStr).as("Count in table " + tableName + " for " + status.getText() + " is not numeric " + countStr).containsOnlyDigits();
				assertThat(percentStr).as("Count in table " + tableName + " for " + status.getText() + " is not numeric " + percentStr).containsOnlyDigits();
			}
		}
		
	}


}
