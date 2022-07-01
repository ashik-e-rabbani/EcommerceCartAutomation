import org.openqa.selenium.remote.DesiredCapabilities;

public class DeviceConfig {

    public DesiredCapabilities setConfig(String aTrue)
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Realme C12");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("appPackage", "com.nopstation.nopcommerce.nopstationcart");
        capabilities.setCapability("appActivity", "com.bs.ecommerce.main.SplashScreenActivity");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("fullReset", "false");

        return capabilities;
    }
}
