package selenium.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ho.demo.Application;
import com.ho.demo.VehicleInfo;


public class VehicleRegCheckPage extends DriverBase {
	
	@FindBy(how = How.XPATH, using = "//*[@id='Vrm']")
    private WebElement registrationTextElement;

    @FindBy(how = How.XPATH, using = "//*[@name='Continue']")
    private WebElement continueButton;

    WebDriver driver;
    
    public static WebDriverWait webdriverWait;
    public VehicleRegCheckPage(WebDriver driver) {
        try {
            this.driver = getDriver();
            webdriverWait = new WebDriverWait(driver, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void vehicleInfosTest() throws IOException {
       Application application = new Application();
       List<VehicleInfo> allVehicleInfos = application.startProcess();
       
       for (VehicleInfo vehicleInfo : allVehicleInfos) {
    	   submitVehicleReg(vehicleInfo);
       }
    }

    public void submitVehicleReg(VehicleInfo vehicleInfo) throws IOException {
        String vehicleRegNumber = vehicleInfo.getRegistrationNumber();
       
        registrationTextElement.click();
        registrationTextElement.sendKeys(vehicleRegNumber);
        continueButton.click();

        VehicleDataResultPage vehicleResultsPage = PageFactory.initElements(driver,VehicleDataResultPage.class);
        vehicleResultsPage.setVehicleInfo(vehicleInfo);
        vehicleResultsPage.chcekVehicleData(vehicleInfo);
        
        File file =  (File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("/Users/mouli/Documents/workspace/demoForHO/src/main/resources/screenshot.png"));
    }

}
