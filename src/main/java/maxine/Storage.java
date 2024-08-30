package maxine;

import maxine.exception.MaxineException;
import maxine.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private String filePath;
    private Parser parser;
    
    public Storage(String filePath) {
        this.filePath = filePath;
        this.parser = new Parser();
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Task task = parser.parse(line);
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oh no! I can't seem to find the file :(");
        } catch (MaxineException e) {
            System.out.println("Oh no! No tasks were found");
        }
        return list;
    }

    public void refreshStorage(TaskList list) {
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
