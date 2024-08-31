package seedu.maxine;

import seedu.maxine.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private String filePath;
    private FileParser fileParser;
    
    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileParser = new FileParser();
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Task task = fileParser.parse(line);
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oh no! I can't seem to find the file :(");
        } 
        return list;
    }

    public void refreshStorage(MaxineList list) {

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
