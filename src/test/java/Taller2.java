import com.github.javafaker.Faker;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Taller2 {
    private final Faker faker = new Faker();
    private AppiumDriver driver;
    List<WebElement> lista;
    int cantTareas = 10;
    String txtPrimerNombre;
    String nombreABuscar;

    @Before
    public void setUp() throws MalformedURLException {
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
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void tallerApp() {

        for (int i = 0; i < cantTareas; i++) {
            //Click agregar tarea
            driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.vrproductiveapps.whendo:id/fab']")).click();

            //Ingresar titulo a la tarea
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextTitle']")).sendKeys(faker.name().title());

            //Ingresar descripcion a la tarea
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextNotes']")).sendKeys(faker.name().lastName());

            //Click guardar
            driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Save']")).click();

            //Espera por el primer elemento de la lista
            WebDriverWait explicitWait = new WebDriverWait(driver, 10);
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[1]")));

        }
        txtPrimerNombre = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[1]")).getText();
        nombreABuscar = driver.findElement(By.xpath("//android.widget.TextView[@text='" + txtPrimerNombre + "']")).getText();
        Assert.assertEquals("Titulo a comprar", txtPrimerNombre, nombreABuscar);
        compararTitulo();

    }

    public void compararTitulo() {
        lista = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']"));
        System.out.println(lista.size());

        //Buscar si se encuentra la tarea
        for (int i = 0; i < lista.size(); i++) {
            String titulo = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[" + (i + 1) + "]")).getText();
            System.out.println("test " + lista.get(i).getText() + "\n");
            if (lista.get(i).getText().equalsIgnoreCase(titulo)) {
            }
        }

    }
}