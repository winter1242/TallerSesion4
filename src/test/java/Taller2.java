import com.github.javafaker.Faker;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Taller2 {
    private final Faker faker = new Faker();
    private AppiumDriver driver;
    List<WebElement> lista;
    int cantTareas = 15;
    String txtPrimerNombre;
    String nombreABuscar;
    String nombreTarea;
    private List<String> nombresTareas = new ArrayList<String>();
    private Boolean enct=false;
    private List<String> nombresListadoTareas = new ArrayList<String>();


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
            nombreTarea=faker.name().title();
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextTitle']")).sendKeys( nombreTarea);
            nombresTareas.add( nombreTarea);

            //Ingresar descripcion a la tarea
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextNotes']")).sendKeys(faker.name().lastName());

            //Click guardar
            driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Save']")).click();

            //Espera por el primer elemento de la lista
            WebDriverWait explicitWait = new WebDriverWait(driver, 10);
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[1]")));

        }

        //Lista antes de scroll
        List<WebElement> listm;
        //Lista luego de scroll
        List<WebElement> listm2=driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']"));


        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();
        TouchAction action = new TouchAction(driver);

        do{
            listm=driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']"));

            listA=recuperarListText(listm);

            //Guardar los elementos de la pestaña;
            for(int i=0;i<listA.size();i++){
                nombresListadoTareas.add(listA.get(i));
            }
            if(listm.size()>=5 && enct==false){
                //Realizar  Scroll
                WebElement start=driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[5]"));
                WebElement end=driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[1]"));

                int startX=start.getLocation().getX();
                int startY=start.getLocation().getY();

                int endX=end.getLocation().getX();
                int endY=end.getLocation().getY();

                action.press(PointOption.point(new Point(startX,startY)))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(new Point(endX,endY)))
                        .release().perform();


            }
            listm2=driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']"));
            listB=recuperarListText(listm2);

        }while (!igualLista(listA,listB));
        for(int i=0;i<nombresListadoTareas.size();i++){
            System.out.println("El nombre de la tarea es : "+nombresListadoTareas.get(i));
        }


        /*
        txtPrimerNombre = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[1]")).getText();
        nombreABuscar = driver.findElement(By.xpath("//android.widget.TextView[@text='" + txtPrimerNombre + "']")).getText();
        Assert.assertEquals("Titulo a comprar", txtPrimerNombre, nombreABuscar);
        compararTitulo();
         */
        compararTitulos();

    }
    public void  compararTitulos(){
        for(int i=0;i<nombresTareas.size();i++){
            Assert.assertTrue("No se encuentra la tarea "+nombresTareas.get(i),nombresListadoTareas.contains(nombresTareas.get(i)));
        }
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
    private Boolean igualLista(List<String> list1 ,List<String> list2){
        Boolean exist=true;

        if(list1.size()==list2.size()){
            for(int i=0;i<list2.size();i++){
                if(!list1.get(i).equals(list2.get(i))){
                    exist=false;
                }
            }
        }
        else {
            exist=false;
        }

        return exist;
    }
    private List<String> recuperarListText(List<WebElement> list1){
        List<String> lista = new ArrayList<String>();
        for(int i=0;i<list1.size();i++){
            lista.add(list1.get(i).getText());
        }
        return lista;
    }

}