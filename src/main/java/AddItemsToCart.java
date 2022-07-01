import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AddItemsToCart {

    static AppiumDriver<MobileElement> appiumDriver;
static WebDriverWait wait;

    public static void main(String[] args) throws MalformedURLException {

        addItemsToCart();

    }



    public static void addItemsToCart() throws MalformedURLException {
        DeviceConfig deviceConfig =new DeviceConfig();

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriver = new AppiumDriver<MobileElement>(url, deviceConfig.setConfig("true"));
        wait = new WebDriverWait(appiumDriver,5);

        //          Accept Terms
//            appiumDriver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnAccept")).click();

        //Horizontal Tap
//        MobileElement element02 = (MobileElement) appiumDriver.findElement(MobileBy.AndroidUIAutomator(
//                "new UiScrollable(new UiSelector().resourceId(\"com.nopstation.nopcommerce.nopstationcart:id/rvHomeCategories\").scrollable(true)).setAsHorizontalList()" +
//                        ".scrollIntoView(new UiSelector().textContains(\"Electronics\"))"));
//        MobileElement part = appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Electronics\")"));
//        MobileElement desired = (MobileElement) wait.until(visibilityOf(part));
//
//        desired.click();

        //Horizontal Tap
        appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.nopstation.nopcommerce.nopstationcart:id/rvHomeCategories\")).setAsHorizontalList().scrollForward()"));

        appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Electronics\"))")).click();


        //Vertical Tap
        appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.nopstation.nopcommerce.nopstationcart:id/rvProductList\")).scrollForward()"));

        appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Nokia Lumia 1020\"))")).click();


        appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //Vertical Tap
        MobileElement quantity = (MobileElement) appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"Quantity\"))"));

//        element.click();

    }
}
