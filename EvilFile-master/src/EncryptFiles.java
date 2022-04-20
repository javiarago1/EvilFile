import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.io.FileUtils;

public class EncryptFiles {

    private static float maximumSizeFile,maximumSizeFolder;
    private static File tempCopiedFiles = new File("C:\\Users\\javier\\Documents\\pruebas");

    private static void setMaximumSizeFile(float size){
        maximumSizeFile=size*1024*1024;
    }

    private static void setMaximumSizeFolder(float size){
        maximumSizeFolder=size*1024*1024;
    }

    public static void main(String[] args) throws IOException {
        new File("C:\\Users\\javier\\Documents\\pruebas\\temp").mkdirs();
        setMaximumSizeFile(200);
        setMaximumSizeFolder(1500);
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);





        ZipFile file = new ZipFile("C:\\Users\\javier\\Documents\\pruebas\\archivo.zip","password".toCharArray());

        createHiddenFolder(new File("C:\\Users\\javier\\Documents\\pruebas\\simulacion"));

        /*
        assert documentFiles != null;
        for (File e:documentFiles){
            if (e.isDirectory() && e.length()<maximumSizeFolder){
                file.addFolder(e,zipParameters);
                System.out.println("Nombre: "+e+" Tamaño: "+e.length());
            }
            else if (e.isFile() && e.length()<maximumSizeFile) {
                file.addFile(e,zipParameters);
                System.out.println("Nombre: "+e+" Tamaño: "+e.length()/1024/1024);
            }
        }
        */

    }

    public static File managePaths (Path absolutePath){
        Path pathBase = Paths.get("C:\\Users\\"+SystemInformation.getSystemName()+"\\Documents\\pruebas\\simulacion");
        return pathBase.relativize(absolutePath).toFile();
    }

    private static void createHiddenFolder(File route) throws IOException {
            File[] fileList = route.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    File parentRoute = managePaths(route.toPath());
                    new File(SystemInformation.tempRoute+ parentRoute +"\\"+ fileList[i].getName()).mkdirs();
                    createHiddenFolder(fileList[i]);
                    //new File("C:\\Users\\"+SystemInformation.getSystemName()+"\\AppData\\Local\\Temp\\TempCache").mkdirs();
                } else {
                    FileUtils.copyFile(fileList[i], new File(SystemInformation.tempRoute + "\\" + fileList[i].getName()));
                }
            }

    }

}
