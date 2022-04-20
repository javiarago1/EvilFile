package system;

public class SystemInformation {

    public static String tempRoute = "C:\\Users\\"+getSystemName()+"\\Documents\\pruebas\\temp\\";

    public static String getSystemName (){
        return System.getProperty("user.name");
    }
}
