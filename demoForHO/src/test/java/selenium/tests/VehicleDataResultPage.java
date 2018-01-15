package selenium.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ho.demo.VehicleInfo;

public class VehicleDataResultPage extends DriverBase {
	private VehicleInfo vehicleInfo;
	WebDriver driver;
    public static WebDriverWait webdriverWait;


    @FindBy(how = How.XPATH, using = "//*[@class='reg-mark']")
    private WebElement regNumber;

    @FindBy(how = How.XPATH, using = "//*[@id='pr3']/div/ul/li[2]/span[2]/strong")
    private WebElement make;

    @FindBy(how = How.XPATH, using = "//*[@id='pr3']/div/ul/li[3]/span[2]/strong")
    private WebElement colour;



    public VehicleDataResultPage(WebDriver driver) {
        try {
            this.driver = getDriver();
            webdriverWait = new WebDriverWait(driver,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public void chcekVehicleData(VehicleInfo vehicleInfo) {
        webdriverWait.until(ExpectedConditions.visibilityOf(regNumber));
        Assert.assertEquals(vehicleInfo.getMake(), make.getText());
        Assert.assertEquals(vehicleInfo.getColour(), colour.getText());
    }

}
