package Utilities;

import java.io.File;
import java.io.IOException;

public class FileChecker {

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
