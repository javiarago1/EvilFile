package threads;

import crypt.EncryptFiles;

import java.io.File;
import java.io.IOException;

public class CopyThreads implements Runnable {

    private Thread thread;
    private final String threadName;
    private final File route;

    public CopyThreads(File route){
        threadName=route.getName();
        this.route=route;
    }

    @Override
    public void run() {
        EncryptFiles file = new EncryptFiles(route);
        file.setMaximumSizeFile(200);
        try {
            file.executeEncryption();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        if (thread==null){
            thread = new Thread(this,threadName);
            thread.start();

        }
    }

}
