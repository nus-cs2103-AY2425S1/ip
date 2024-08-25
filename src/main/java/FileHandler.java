import java.io.File;
import java.io.IOException;

public class FileLogic {
    File tasksFile;

    public FileLogic(String filePath) {
        tasksFile = new File(filePath);
        try {
            boolean canMakeDirectory = tasksFile.getParentFile().mkdirs();
            boolean canCreateNewFile = tasksFile.createNewFile();
            if (canMakeDirectory && canCreateNewFile) {
                System.out.println("Creating new Tasks File for you at: " + tasksFile.getPath());
            } else {
                System.out.println("Unable to make directory or create new file");
            }
        } catch (IOException e) {
            System.out.println("Unable to create new tasks file: " + e);
        }
    }
}
