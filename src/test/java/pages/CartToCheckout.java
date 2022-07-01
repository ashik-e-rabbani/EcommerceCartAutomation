package pages;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import config.DeviceConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.ReadDataFromExternal;
import utils.UIElements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CartToCheckout {
    DeviceConfig deviceConfig =new DeviceConfig();
    public static AppiumDriver appiumDriver;
    static ExtentTest logger;
    static ExtentReports report;
    UIElements ue;
    ReadDataFromExternal readDataFromExternal;
    @BeforeSuite
    public void setup() throws MalformedURLException, NullPointerException {
        DesiredCapabilities dc = new DesiredCapabilities();
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriver = new AppiumDriver<MobileElement>(url, deviceConfig.setConfig("true","false"));
        ue = new UIElements();
        report = new ExtentReports(ue.REPORT_PATH + "/cartToCheckoutReport.html", true);
        readDataFromExternal = new ReadDataFromExternal();
    }

    @Test(description = "Mike go to shopping cart by clicking top cart icon", priority = 0)
    public void clickCart() throws InterruptedException {
        logger = report.startTest("Tap Cart icon");
        Thread.sleep(5000);
            MobileElement clickCart = (MobileElement) appiumDriver.findElementById(ue.CART_BTN);
            if (clickCart.isDisplayed()) {
                clickCart.click();
                logger.log(LogStatus.PASS, "Cart icon tapped");
                Thread.sleep(3000);
            }
    } // End of clickCart

    @Test (description = "Mike click checkout button from shopping cart page", priority = 1)
    public void clickCheckout() throws InterruptedException {
        logger = report.startTest("Tap on Checkout");
        Thread.sleep(5000);
        MobileElement clickCheckout = (MobileElement) appiumDriver.findElementById(ue.CHECKOUT_ITEM);
        if (clickCheckout.isDisplayed()) {
            clickCheckout.click();
            logger.log(LogStatus.PASS, "Tapped on checkout button");
        }

    } // End of clickCheckout

    @Test ( description = "Mike select checkout as guest from shopping cart page", priority = 2)
    public void checkoutGuest() throws InterruptedException {
        logger = report.startTest("Tap on Checkout Guest");
        Thread.sleep(5000);
        MobileElement checkoutGuest = (MobileElement) appiumDriver.findElementById(ue.CHECKOUT_GUEST);
        if (checkoutGuest.isDisplayed()) {
            checkoutGuest.click();
            logger.log(LogStatus.PASS, "Tapped on checkout guest button");
        }
    } // End of checkoutGuest

    @Test (description = "Mike input all the details in checkout billing details page and click continue", priority = 3)
    public void fillForm() throws InterruptedException, IOException {
        logger = report.startTest("Filling Billing Info");
        List ed = readDataFromExternal.externalDataComma();
        Thread.sleep(5000);

        // Name
        MobileElement firstName = (MobileElement) appiumDriver.findElementById(ue.F_NAME);
        if (firstName.isDisplayed())
        {
            firstName.sendKeys(ed.get(0).toString());
            Thread.sleep(1000);
        }

        MobileElement lastName = (MobileElement) appiumDriver.findElementById(ue.L_NAME);
        if (lastName.isDisplayed())
        {
            lastName.sendKeys(ed.get(1).toString());
            logger.log(LogStatus.PASS, "Name Added");
            Thread.sleep(2000);
        }

        // Email address
        MobileElement email = (MobileElement) appiumDriver.findElementById(ue.EMAIL);
        if (email.isDisplayed())
        {
            email.sendKeys(ed.get(2).toString());
            logger.log(LogStatus.PASS, "Email Added");
            Thread.sleep(2000);
        }

        // Country
        MobileElement countrySelector = (MobileElement) appiumDriver.findElementById(ue.COUNTRY_SPIN);
        if (countrySelector.isDisplayed())
        {
            countrySelector.click();
            Thread.sleep(2000);
        }

        MobileElement selectCountry = (MobileElement) appiumDriver.findElementByXPath(ue.COUNTRY_S);
        if (selectCountry.isDisplayed())
        {
            selectCountry.click();
            logger.log(LogStatus.PASS, "Country Added");
            Thread.sleep(4000);
        }

        // State
        MobileElement stateSelector = (MobileElement) appiumDriver.findElementById(ue.STATE_SPIN);
        if (stateSelector.isDisplayed())
        {
            stateSelector.click();
            Thread.sleep(2000);
        }

        MobileElement selectState = (MobileElement) appiumDriver.findElementByXPath(ue.STATE_S);
        if (selectState.isDisplayed())
        {
            selectState.click();
            logger.log(LogStatus.PASS, "State Added");
            Thread.sleep(2000);
        }

        // Company Info
        MobileElement company = (MobileElement) appiumDriver.findElementById(ue.COMPANY);
        if (company.isDisplayed())
        {
            company.sendKeys(ed.get(3).toString());
            logger.log(LogStatus.PASS, "Company info Added");
            Thread.sleep(3000);
        }

        //Let's move to Fax for better reach

        appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Fax\"))"));
        Thread.sleep(2000);

        // city
        MobileElement setCity = (MobileElement)appiumDriver.findElementById(ue.CITY);
        if (setCity.isDisplayed())
        {
            setCity.sendKeys(ed.get(4).toString());
            logger.log(LogStatus.PASS, "City Added");
            Thread.sleep(1000);
        }

        //

        // Address One
        MobileElement sAddressOne = (MobileElement)appiumDriver.findElementById(ue.ADDRESS_O);
        if (sAddressOne.isDisplayed())
        {
            sAddressOne.sendKeys(ed.get(5).toString());
            Thread.sleep(1000);
        }

        // Address two
        MobileElement sAddressTwo = (MobileElement)appiumDriver.findElementById(ue.ADDRESS_T);
        if (sAddressTwo.isDisplayed())
        {
            sAddressTwo.sendKeys(ed.get(6).toString());
            logger.log(LogStatus.PASS, "Address Added");
            Thread.sleep(1000);
        }

        // Zip code
        MobileElement zipCode = (MobileElement)appiumDriver.findElementById(ue.ZIP);
        if (zipCode.isDisplayed())
        {
            zipCode.sendKeys(ed.get(7).toString());
            logger.log(LogStatus.PASS, "Zip Added");
            Thread.sleep(1000);
        }

        // Phone
        MobileElement phone = (MobileElement)appiumDriver.findElementById(ue.PHONE);
        if (phone.isDisplayed())
        {
            phone.sendKeys(ed.get(8).toString());
            logger.log(LogStatus.PASS, "Phone Added");
            Thread.sleep(1000);
        }

        // Fax
        MobileElement fax = (MobileElement)appiumDriver.findElementById(ue.FAX);
        if (fax.isDisplayed())
        {
            fax.sendKeys(ed.get(9).toString());
            logger.log(LogStatus.PASS, "Fax Added");
            Thread.sleep(1000);
        }

        // Continue to shipping
        MobileElement continueShipping = (MobileElement) appiumDriver.findElementById(ue.CONTINUE_SHIP);
        if (continueShipping.isDisplayed())
        {
            continueShipping.click();
            logger.log(LogStatus.PASS, "Proceed to Ship");
            Thread.sleep(3000);
        }

    } // End of fillForm

    @Test (priority = 4)
    public void prepareShipping() throws InterruptedException {
        logger = report.startTest("prepare Shipping");
        MobileElement chooseShipping = (MobileElement)  appiumDriver.findElementByXPath("\t\n" +
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/com.bs.ecommerce.customViews.RadioGridGroupforReyMaterial/android.widget.RelativeLayout[3]");
        if (chooseShipping.isDisplayed())
        {
            chooseShipping.click();
            Thread.sleep(2000);
        }

        //Let's scroll for better reach

        appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
        Thread.sleep(2000);

        // Tap on continue
        MobileElement doContinue = (MobileElement)  appiumDriver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/btnContinue");
        if (doContinue.isDisplayed())
        {
            doContinue.click();
            logger.log(LogStatus.PASS, "prepare Shipping completed");
            Thread.sleep(5000);
        }
    } // End of prepareShipping

    @Test (priority = 5)
    public void doPayment() throws InterruptedException {

        //Let's move to Cheque/Money order for better reach
        logger = report.startTest("Do Payment");
        MobileElement selectCorM = (MobileElement)  appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Check / Money Order\"))"));
        Thread.sleep(2000);

        if (selectCorM.isDisplayed())
        {
            selectCorM.click();
            Thread.sleep(2000);
        }

        //Let's move to contiune paayment for better reach

        appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward(5)"));
        Thread.sleep(2000);
        MobileElement continuePayment = (MobileElement)  appiumDriver.findElementById(ue.CONTINUE_PAYMENT);
        if (continuePayment.isDisplayed())
        {
            continuePayment.click();
            logger.log(LogStatus.PASS, "Proceed to continue Payment");
            Thread.sleep(5000);
        }

        //Tap Next
//        MobileElement tapNext = (MobileElement)  appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View/android.view.View[2]/android.widget.Button");
//        if (tapNext.isDisplayed())
//        {
//            tapNext.click();
//            Thread.sleep(3000);
//        }

        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction.tap(PointOption.point(533,1428)).perform();
        logger.log(LogStatus.PASS, "Tapped WebView Next Button");
        Thread.sleep(5000);
    } // End of prepareShipping

    @Test (priority = 6)
    public void confirmPayment() throws InterruptedException {
        logger = report.startTest("confirm Payment");
        MobileElement confirmPayment = (MobileElement) appiumDriver.findElementById(ue.CONFIRM_PAYMENT);
        if (confirmPayment.isDisplayed()) {
            confirmPayment.click();
            logger.log(LogStatus.PASS, "Payment Confirmed");
            Thread.sleep(5000);

        }
    }

    @Test (priority = 7)
    public void verifyOrder() throws InterruptedException {
        logger = report.startTest(" Validating Successions");
        MobileElement popMessage = (MobileElement) appiumDriver.findElementById(ue.POP_MSG);
        if (popMessage.isDisplayed()) {
           String popupResponse =  popMessage.getText();
            Assert.assertTrue(popupResponse.contains("Your order has been successfully processed!"),"Something went wrong");
            Thread.sleep(2000);
            logger.log(LogStatus.PASS, "Validation Successful");

        }


    }

    @AfterSuite
    public  void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        report.endTest(logger);
        report.flush();
        appiumDriver.quit();
    }

}
