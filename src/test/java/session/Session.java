package session;
// 1 attributo del mismo tipo de clase
// 2 construtor debe ser privado
// 3 tener un metodo public estatico que devuelta una instancia de la clase


import factoryDevice.FactoryDevice;
import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;

public class Session {
    private static Session mySession = null;
    private AppiumDriver driver;

    private Session() throws MalformedURLException {
        this.driver = FactoryDevice.make(FactoryDevice.ANDROID).create();
    }

    public static Session getInstance() throws MalformedURLException {

        if (mySession == null) {
            mySession = new Session();
        }
        return mySession;
    }

    public void killSession() {
        driver.quit();
        mySession = null;
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}