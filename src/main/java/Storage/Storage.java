package Storage;

import Parser.Parser;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Task.Task;

public class Storage {

    protected File storageFile;

    public Storage(File storageFile) {
        this.storageFile = storageFile;
    }

    public static Storage createStorage(String path) {
        File file = new File(path);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdir();
        }

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("error when creating file");
                e.printStackTrace();
            }
        }
        return new Storage(file);
    }

    public File getStorageFile() {
        return storageFile;
    }
}
