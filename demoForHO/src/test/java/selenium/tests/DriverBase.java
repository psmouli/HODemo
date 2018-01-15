package selenium.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class DriverBase {
	private static WebDriver driver;
	
	
	public static  WebDriver getDriver() throws Exception {
		if (driver == null) {
			driver = new FirefoxDriver();
		
		}
		return driver;
		
	}
	
	@AfterMethod(alwaysRun = true)
    public void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }


}
