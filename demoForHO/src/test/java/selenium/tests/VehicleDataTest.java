package selenium.tests;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

public class VehicleDataTest extends DriverBase {
   
	public static void main(String[] args) throws Exception{
        VehicleDetailsHomePage homepage = PageFactory.initElements(getDriver(), VehicleDetailsHomePage.class);
        homepage.navigateTo("https://www.gov.uk/get-vehicle-information-from-dvla");
        VehicleRegCheckPage vehicleRegCheckPage = homepage.clickStartNowButton();

        vehicleRegCheckPage.vehicleInfosTest();
        TimeUnit.SECONDS.sleep(10);
        
     }
    
}
