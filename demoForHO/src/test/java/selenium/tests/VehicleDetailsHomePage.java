package selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
public class VehicleDetailsHomePage extends DriverBase {
	
	WebDriver driver;
   
    public VehicleDetailsHomePage(WebDriver driver) {
        try {
            this.driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testVehicleData() throws Exception {
      driver.get("www.gov.uk/get-vehicle-information-from-dvla");

    }


    public void navigateTo(String url) {
        driver.get(url);
    }

    public VehicleRegCheckPage clickStartNowButton() throws Exception{
    	driver.findElement(By.linkText("Start now")).click();

        TimeUnit.SECONDS.sleep(5);
        VehicleRegCheckPage vehicleServicesPage = PageFactory.initElements(driver, VehicleRegCheckPage.class);
        return vehicleServicesPage;
    }

}
