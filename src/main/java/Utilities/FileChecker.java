package Utilities;

import java.io.File;
import java.io.IOException;

/**
 * Checking and creating necessary files and directories.
 */
public class FileChecker {

    /**
     * Creates a new FileChecker object and ensures that the specified file and its directory exist.
     * If the directory or file does not exist, they will be created.
     *
     * @param filePath The path of the file to check and create if necessary.
     */
    public FileChecker(String filePath) {
        File file = new File(filePath);
        File directory = new File(file.getParent());

        try{
            if(!directory.exists()){
                boolean isCreated = directory.mkdir();
                if (!isCreated){
                    System.out.println("Issue creating Directory");
                }
            }
            if(!file.exists()){
                if(!file.createNewFile()){
                    System.out.println("Issue creating File.");
                }
            }
        }catch (IOException e) {
            System.out.println("Error in file creation.");
        }
    }
}
