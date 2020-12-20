package controlAppium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import session.Session;

import java.net.MalformedURLException;

public class Control {
    protected WebElement control;
    protected By locator;

    public Control(By locator) {
        this.locator = locator;
    }

    protected void find() throws MalformedURLException {
        this.control = Session.getInstance().getDriver().findElement(this.locator);
    }

    public void click() throws MalformedURLException {
        this.find();
        this.control.click();
    }

    public void type(String value) throws MalformedURLException {
        this.find();
        this.control.sendKeys(value);

    }

    public boolean isDisplayedControl() {
        try {
            this.find();
            return this.control.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public String getTextControl() throws MalformedURLException {
        this.find();
        return this.control.getText();
    }


}
