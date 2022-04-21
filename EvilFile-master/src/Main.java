import crypt.EncryptFiles;
import files.FilesManagement;
import system.SystemInformation;
import threads.CopyThreads;

import java.io.*;

public class Main {
    static File [] files = new File[]{
            new File("C:\\Users\\javier\\Documents\\pruebas\\simulacion"),
            new File("C:\\Users\\javier\\Documents\\pruebas\\simulacion2")
    };
    public static void main(String[] args) throws IOException {
        FilesManagement.createTempFolder();

        for (File file:files){
            new CopyThreads(file).start();
        }

    }
}
