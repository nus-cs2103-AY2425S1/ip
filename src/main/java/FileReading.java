import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileReading {
    
    public FileReading() {
        //  nothing
    }
    
    public static void rememberFileContents(String filePath, ArrayList<Task> list) {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String task = s.nextLine();
                TaskParser.parseTask(task, list);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oh no! I can't seem to find the file :(");
        }
        
    }
}
