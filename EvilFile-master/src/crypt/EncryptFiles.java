package crypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.spec.ECField;

import files.FilesManagement;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.io.FileUtils;

import system.SystemInformation;


public class EncryptFiles {


    private float maximumSizeFile;
    private final String folderName;
    private final File route;
    ZipParameters zipParameters = new ZipParameters();


    public void setMaximumSizeFile(float size){
        maximumSizeFile=size*1024*1024;
    }

    public EncryptFiles (File route){
        this.route=route;
        folderName=route.getName();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
    }


    public void executeEncryption() throws IOException{
        FilesManagement.createTempFolder();
        FilesManagement.createFolderName(route);
        copyFiles(route);
        encryptFiles();
    }



    private void copyFiles(File route) throws IOException {
            File[] fileList = route.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            System.out.println(file);
            if (file.isDirectory()) {
                File parentRoute = FilesManagement.managePaths(route.toPath(),this.route);
                new File(SystemInformation.tempRoute +"\\"+folderName+"\\"+ parentRoute + "\\" + file.getName()).mkdirs();
                copyFiles(file);
            } else {
                if (file.length() <= maximumSizeFile) {
                    FileUtils.copyFile(file, new File(SystemInformation.tempRoute + "\\" + folderName+"\\"+file.getName()));
                }
            }
        }
    }

    private void encryptFiles() throws ZipException {
        ZipFile file = new ZipFile(route+"\\"+folderName+".zip","password".toCharArray());
        file.addFolder(new File(SystemInformation.tempRoute+"\\"+folderName),zipParameters);
    }

}

