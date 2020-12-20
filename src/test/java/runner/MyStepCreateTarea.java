package runner;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyStepCreateTarea {
    private Boolean enct=false;
    private List<String> nombresListadoTareas = new ArrayList<String>();

    @Given("la aplicacion when do esta abierta")
    public void laAplicacionWhenDoEstaAbierta() {
        // implicit wait  (tiempo para todos los controles)
        Runner.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @When("click en el boton [AgregarTarea]")
    public void clickEnElBotonAgregarTarea() {
        //Click agregar tarea
        Runner.driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.vrproductiveapps.whendo:id/fab']")).click();
    }

    @And("Ingresar a la caja de texto titulo de la tarea : {}")
    public void ingresarALaCajaDeTextoDelNombreDeLaTareaTitulo(String valor) {
        Runner.driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(valor);
    }
    @And("Ingresar a la caja de texto descripcion de la tarea : {}")
    public void ingresarALaCajaDeTextoDeDescripcionDeLaTareaTitulo(String valor) {
        Runner.driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(valor);
    }
    @And("Clic en [GuardarTarea]")
    public void clicEnGuardarTarea() {
        Runner.driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Save\"]")).click();
    }

    @And("Visualizar las tareas creadas")
    public void visualizarLasTareasCreadas() {
        //Lista antes de scroll
        List<WebElement> listm;
        //Lista luego de scroll
        List<WebElement> listm2=Runner.driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']"));


        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();
        TouchAction action = new TouchAction(Runner.driver);

        do{
            listm=Runner.driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']"));

            listA=recuperarListText(listm);

            //Guardar los elementos de la pestaña;
            for(int i=0;i<listA.size();i++){
                nombresListadoTareas.add(listA.get(i));
            }

            if(listm.size()>=5 ){
                //Realizar  Scroll
                WebElement start=Runner.driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[5]"));
                WebElement end=Runner.driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text'])[1]"));

                int startX=start.getLocation().getX();
                int startY=start.getLocation().getY();

                int endX=end.getLocation().getX();
                int endY=end.getLocation().getY();

                action.press(PointOption.point(new Point(startX,startY)))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(new Point(endX,endY)))
                        .release().perform();


            }
            listm2=Runner.driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']"));
            listB=recuperarListText(listm2);

        }while (!igualLista(listA,listB));

    }

    @Then("la tarea {} debe estar en pantalla")
    public void laTareaTituloDebeEstarEnPantalla(String valor) {
        if(nombresListadoTareas.contains(valor.trim())){
            enct=true;
        }
        Assert.assertTrue("!!!No se encontro la tarea "+valor,enct);
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
