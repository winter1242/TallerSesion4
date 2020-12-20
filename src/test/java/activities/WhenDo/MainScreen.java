package activities.WhenDo;

import controlAppium.Button;
import controlAppium.Label;
import org.openqa.selenium.By;

public class MainScreen {

    public Button addTarea= new Button(By.xpath("//android.widget.ImageButton[@resource-id='com.vrproductiveapps.whendo:id/fab']"));
    public Label nameTarea=new Label(By.id("com.vrproductiveapps.whendo:id/home_list_item_text"));
    public MainScreen(){

    }
}
