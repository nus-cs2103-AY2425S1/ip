package Main;

import java.io.File;
import java.io.IOException;

public class FileUI {
    public static final String FILE_PATH = "./data/flash.txt";

    /**
     * Creates new file if file does not exist
     * catches relevant exception
     *
     */
    public static void createFileIfNotPresent() {
        File file = new File(FILE_PATH); //initializes a file object in path
        File parentDir = file.getParentFile(); //get dir of file

        //if dir doesn't exists, create directory
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }

        try {
            //if file doesn't exist, create file
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file" + e.getMessage());
        }
    }
}
