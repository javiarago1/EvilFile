package files;

import org.apache.commons.io.FileUtils;
import system.SystemInformation;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

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

    public static void removeFiles(File route) throws IOException{
        File[]fileList = route.listFiles();
        assert fileList != null;
        for (File file:fileList){
            if (file.isDirectory()){
                removeFiles(file);
                FileUtils.deleteDirectory(file);
            }
            else {
                secureDelete(file);
            }
        }
    }




    /*
    *
    * Taken from https://stackoverflow.com/questions/858036/how-to-securely-delete-files-in-java
    *
    * */
    public static void secureDelete(File file) throws IOException {
        if (file.exists()) {
            long length = file.length();
            SecureRandom random = new SecureRandom();
            RandomAccessFile raf = new RandomAccessFile(file, "rws");
            raf.seek(0);
            raf.getFilePointer();
            byte[] data = new byte[64];
            int pos = 0;
            while (pos < length) {
                random.nextBytes(data);
                raf.write(data);
                pos += data.length;
            }
            raf.close();
            file.delete();
        }
    }
}
