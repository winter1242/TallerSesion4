package activities.WhenDo;

import controlAppium.Button;
import controlAppium.TextBox;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;

public class FormTarea {
    public TextBox titleTarea=new TextBox(By.id("com.vrproductiveapps.whendo:id/noteTextTitle"));
    public TextBox descriptionTarea=new TextBox(By.id("com.vrproductiveapps.whendo:id/noteTextNotes"));
    public Button saveButton=new Button(By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"));

    public FormTarea(){}
}
