import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File archivos = new File("C:\\Users\\javier\\Documents");
        File []listoffles = archivos.listFiles();

        assert listoffles != null;
        for (File e:listoffles){
            System.out.println(e);

        }

        new File("C:\\Users\\javier\\Documents\\hola").mkdirs();
        File ruta_copiado = new File("C:\\Users\\javier\\Documents\\hola");

        String archivo = "tema 5.pdf";

        for (File e:listoffles){
            copy(e, new File(ruta_copiado + e.getName()));
        }



        copy(new File("C:\\Users\\javier\\Documents\\"+archivo), new File("C:\\Users\\javier\\Documents\\hola\\"+archivo));

    }


    public static void copy(File recieved, File destination) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(recieved);
        FileOutputStream fileOutputStream = new FileOutputStream(destination);

        int bufferSize;
        byte[] bufffer = new byte[512];
        while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
            fileOutputStream.write(bufffer, 0, bufferSize);
        }
        fileInputStream.close();
        fileOutputStream.close();

    }
}
