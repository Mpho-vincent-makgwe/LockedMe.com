package com.lockedme;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {
    private final String rootDirectory;

    public FileManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public List<String> getAllFiles() {
        File directory = new File(rootDirectory);
        List<String> fileList = new ArrayList<>();
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    fileList.add(file.getName());
                }
            }
        }
        Collections.sort(fileList);
        return fileList;
    }

    public boolean addFile(String fileName) {
        File file = new File(rootDirectory, fileName);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFile(String fileName) {
        File file = new File(rootDirectory, fileName);
        return file.exists() && file.delete();
    }

    public boolean searchFile(String fileName) {
        File file = new File(rootDirectory, fileName);
        return file.exists();
    }
}
