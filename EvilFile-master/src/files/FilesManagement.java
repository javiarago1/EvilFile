package files;

import system.SystemInformation;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesManagement {
    public static void createTempFolder(){
        new File(SystemInformation.tempRoute).mkdirs();
    }
    public static void createFolderName(File route){
        new File(SystemInformation.tempRoute+"\\"+route.getName()).mkdirs();
    }
    public static File managePaths (Path absolutePath, File route){
        Path pathBase = Paths.get(route.toString());
        return pathBase.relativize(absolutePath).toFile();
    }
}
