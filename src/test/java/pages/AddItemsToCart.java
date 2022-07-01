package pages;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import config.DeviceConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.UIElements;

import java.net.MalformedURLException;
import java.net.URL;

public class AddItemsToCart {
    DeviceConfig deviceConfig = new DeviceConfig();
    public static AppiumDriver appiumDriver;
    static ExtentTest logger;
    static ExtentReports report;
    UIElements ue;

    @BeforeSuite
    public void setup() throws MalformedURLException, NullPointerException {
        DesiredCapabilities dc = new DesiredCapabilities();
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriver = new AppiumDriver<MobileElement>(url, deviceConfig.setConfig("true", "false"));
        ue = new UIElements();
        report = new ExtentReports(ue.REPORT_PATH + "/AddItemsReport.html", true);
    }

    @Test(description = "Mike is accepting Terms and Conditions", priority = 0)
    public void acceptTc() throws InterruptedException {
        logger = report.startTest("Accepting Terms and Conditions");
        Thread.sleep(5000);
        try {
            MobileElement acceptTcBtn = (MobileElement) appiumDriver.findElementById(ue.ACCEPT_TC);
            if (acceptTcBtn.isDisplayed()) {
                acceptTcBtn.click();
                logger.log(LogStatus.PASS, "Mike has Accepted T and C");
            }
        } catch (NoSuchElementException e) {
            logger.log(LogStatus.ERROR, "Error occurred");
            e.printStackTrace();
        }
    } // End of acceptTc

    @Test(description = "Mike click \"electronics\" from our categories list from home page", priority = 1)
    public void chooseCategory() {

        //Choose Electronics from our categories | Horizontal Scroll
        logger = report.startTest("Mike click \"electronics\" from our categories list from home page");
        try {
            appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.nopstation.nopcommerce.nopstationcart:id/rvHomeCategories\")).setAsHorizontalList().scrollForward()"));
            MobileElement chooseCategory = (MobileElement) appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Electronics\"))"));
            if (chooseCategory.isDisplayed()) {
                chooseCategory.click();
                logger.log(LogStatus.PASS, "Category chose successfully");
            }
            Thread.sleep(2500);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(LogStatus.ERROR, "Error occurred");
        }

    } // End of chooseCategory

    @Test(description = "Mike click to \"Nokia Lumia 1020\" product details page", priority = 2)
    public void chooseItems() throws InterruptedException {
        logger = report.startTest("Mike click to \"Nokia Lumia 1020\" product details page");
        // Finding Device | Vertical Scroll
        appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.nopstation.nopcommerce.nopstationcart:id/rvProductList\")).scrollForward(1)"));
        MobileElement chooseItems = (MobileElement) appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Nokia Lumia 1020\"))"));
        if (chooseItems.isDisplayed()) {
            chooseItems.click();
            logger.log(LogStatus.PASS, "Clicked");
            Thread.sleep(2000);
        }
    } // End of chooseItems

    @Test(description = "Mike select size \"Large\" from product details page, Mike click plus button to increase Qty by \"2\"", priority = 3)
    public void customizeItem() throws InterruptedException {
        logger = report.startTest("Customize Item");
        // Scroll to target section
        appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Quantity\"))"));
        Thread.sleep(2000);

        // Increase quantity
        MobileElement increaseQuantity = (MobileElement) appiumDriver.findElementById(ue.PLUS_BTN);
        if (increaseQuantity.isDisplayed()) {
            increaseQuantity.click();
            logger.log(LogStatus.PASS, "increased quantity");
        }

        // Tap size section
        MobileElement chooseSize = (MobileElement) appiumDriver.findElementById(ue.SIZE);
        if (chooseSize.isDisplayed()) {
            chooseSize.click();
            Thread.sleep(3000);
        }

        // Select size
        MobileElement largeSize = (MobileElement) appiumDriver.findElementByXPath(ue.LARGE);
        if (largeSize.isDisplayed()) {
            largeSize.click();
            logger.log(LogStatus.PASS, "Size Selected");
            Thread.sleep(3000);
        }

        // Confirm customization
        MobileElement confirmCustomization = (MobileElement) appiumDriver.findElementById(ue.DONE);
        if (confirmCustomization.isDisplayed()) {
            confirmCustomization.click();
            logger.log(LogStatus.PASS, "Confirm customization");
            Thread.sleep(3000);
        }

    } // End of customizeItem

    @Test(description = "Mike click add to cart button to add the product in his cart", priority = 4)
    public void addToCart() {
        logger = report.startTest("Add To Cart");
        MobileElement btnAddToCart = (MobileElement) appiumDriver.findElementById(ue.ADD_TO_CART);
        if (btnAddToCart.isDisplayed()) {
            btnAddToCart.click();
            logger.log(LogStatus.PASS, "Added");
        }
    } // End of addToCart


    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        report.endTest(logger);
        report.flush();
        appiumDriver.quit();
    }

}