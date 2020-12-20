package runner;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import session.Session;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
public class Runner {

    //Hooks
    public static AppiumDriver driver;
    @Before
    public void beforeScenario() throws MalformedURLException {

    }

    @After
    public void afterScenario(Scenario scenario) throws MalformedURLException {
        if (scenario.isFailed()){
            // appium
            byte[] screenShoot= Session.getInstance().getDriver().getScreenshotAs(OutputType.BYTES);

            // cucumber
            scenario.attach(screenShoot,"image/png","appium screenshot");
        }


        Session.getInstance().killSession();
    }
}
