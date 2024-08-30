import task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private String filePath;
    
    public Storage() {
        this.filePath = "data/maxine.txt";
    }

    public void load(String filePath, ArrayList<Task> list) {
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

    public void refreshStorage(ArrayList<Task> list) {
        File file = new File(filePath);

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
