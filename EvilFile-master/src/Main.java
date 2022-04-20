import crypt.EncryptFiles;
import system.SystemInformation;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        EncryptFiles file = new EncryptFiles(new File("C:\\Users\\"+ SystemInformation.getSystemName() +"\\Documents\\pruebas\\simulacion"));
        file.setMaximumSizeFile(200);
        file.executeEncryption();

    }
}
