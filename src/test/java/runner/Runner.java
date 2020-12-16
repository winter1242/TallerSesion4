package runner;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
public class Runner {

    //Hooks
    public static AppiumDriver driver;
    @Before
    public void beforeScenario() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Emulador Pixel 4
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android SDK built for x86");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("appPackage", "com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity", ".ui.HomeActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void afterScenario(){

        driver.quit();
    }
}
