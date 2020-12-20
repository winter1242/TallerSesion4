package runner;

import activities.WhenDo.FormTarea;
import activities.WhenDo.MainScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MyStepCreateTarea {
    private FormTarea formTarea=new FormTarea();
    private MainScreen mainScreen=new MainScreen();

    private Boolean enct=false;
    private List<String> nombresListadoTareas = new ArrayList<String>();

    @Given("la aplicacion when do esta abierta")
    public void laAplicacionWhenDoEstaAbierta() {
    }

    @When("click en el boton [AgregarTarea]")
    public void clickEnElBotonAgregarTarea() throws MalformedURLException {
        if(mainScreen.addTarea.isDisplayedControl())
            mainScreen.addTarea.click();
    }

    @And("Ingresar a la caja de texto titulo de la tarea : {}")
    public void ingresarALaCajaDeTextoDelNombreDeLaTareaTitulo(String valor) throws MalformedURLException {
        formTarea.titleTarea.type(valor);
    }
    @And("Ingresar a la caja de texto descripcion de la tarea : {}")
    public void ingresarALaCajaDeTextoDeDescripcionDeLaTareaTitulo(String valor) throws MalformedURLException {
        formTarea.descriptionTarea.type(valor);
    }
    @And("Clic en [GuardarTarea]")
    public void clicEnGuardarTarea() throws MalformedURLException {
        formTarea.saveButton.click();
    }

    @Then("la tarea {} debe estar en pantalla")
    public void laTareaTituloDebeEstarEnPantalla(String valor) throws MalformedURLException {
        List<WebElement> list=mainScreen.nameTarea.listElement();
        if(list.contains(valor.trim())){
            enct=true;
        }
        Assert.assertTrue("!!!No se encontro la tarea "+valor,enct);
    }
}
