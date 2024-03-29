package testrunner;

import java.io.File;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features",
			     glue={"stepdefinitions"},
			     plugin= {"pretty","com.cucumber.listener.ExtentCucumberFormatter:Report/TestExecutionReport.html"},
			     dryRun=false,
			     monochrome=true
				 )

public class TestRun {
	
	@AfterClass
	 public static void writeExtentReport() {
		File file = new File(System.getProperty("user.dir") + "\\" + "Configuration" + "\\" + "extent-config.xml" );
	 }
	
	
	
}

