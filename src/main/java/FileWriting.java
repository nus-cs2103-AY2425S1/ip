import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriting {
    
    public static String path = "data/maxine.txt";
    
    public FileWriting() {
        // nothing
    }

    public static void refreshFile(ArrayList<Task> list) {
        File file = new File(path);
        
        try (BufferedWriter writer = 
                     new BufferedWriter(new FileWriter(file, false))) {
            for (Task item : list) {
                writer.write(item.writeToFile());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.print("Error in File Writing java\n");
        } 
    }
    
}
