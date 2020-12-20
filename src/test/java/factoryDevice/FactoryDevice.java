package factoryDevice;

public class FactoryDevice {

    public static final String ANDROID="android";
    public static final String IOS="ios";
    public static final String WINDOWS_PHONE="windowsphone";
    public static final String BROWSER_STACK="browserstack";

    public static IDevice make(String typeDevice){
        IDevice device;

        switch (typeDevice){
            case ANDROID:
                device= new Android();
                break;
            case IOS:
                device= new Ios();
                break;
            case WINDOWS_PHONE:
                device= new WindowPhone();
                break;
            case BROWSER_STACK:
                device= new AndroidCloud();
                break;
            default:
                device= new Android();
                break;

        }

        return device;

    }

}
