package activities.WhenDo;

import controlAppium.Button;
import org.openqa.selenium.By;

public class MainScreen {

    public Button addTarea= new Button(By.xpath("//android.widget.ImageButton[@resource-id='com.vrproductiveapps.whendo:id/fab']"));

    public MainScreen(){

    }
}
