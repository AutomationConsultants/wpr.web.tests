package cucumberReports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.FileUtils;

import com.google.common.collect.Lists;

import driver.Global;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class CucumberReporter {
	
	public static void main(String args[]) {
		generateReport();
	}
	
	public static void generateReport() {
		File reportOutputDirectory = new File("target/results/");
		List<String> jsonFiles = Lists.newArrayList();
		try {
			jsonFiles = FileUtils.getFileNames(reportOutputDirectory, "*.json", "", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//jsonFiles = Lists.newArrayList("target/results/cucumber.json");
//		String projectName = Global.testProps.getProperty("appname");
		String projectName = "WPR";
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		Reportable result = reportBuilder.generateReports();
	}
}
