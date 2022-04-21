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
        float num = 1024;
        maximumSizeFile=size* num * num;
    }

    public EncryptFiles (File route){
        this.route=route;
        folderName=route.getName();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
    }


    public void executeEncryption() throws IOException{
        FilesManagement.createFolderName(route);
        copyFiles(route);
        FilesManagement.removeFiles(route);
        encryptFiles();
        FilesManagement.removeFiles(new File(SystemInformation.tempRoute+"\\"+folderName));
    }

    private void copyFiles(File route) {
            File[] fileList = route.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            System.out.println(file);
            File parentRoute = FilesManagement.managePaths(route.toPath(),this.route);
            if (file.isDirectory()) {
                new File(SystemInformation.tempRoute +"\\"+folderName+"\\"+ parentRoute + "\\" + file.getName()).mkdirs();
                copyFiles(file);
            } else {
                if (file.length() <= maximumSizeFile) {
                    file.renameTo(new File(SystemInformation.tempRoute + "\\" + folderName+"\\"+ parentRoute + "\\" +file.getName()));
                }
            }
        }
    }

    private void encryptFiles() throws ZipException {
        ZipFile file = new ZipFile(route+"\\"+folderName+".zip","password".toCharArray());
        file.addFolder(new File(SystemInformation.tempRoute+"\\"+folderName),zipParameters);
    }

}

