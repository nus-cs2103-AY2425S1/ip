package Storage;

import Task.Task;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Task.*;
public class Storage {
    private static final String splitBy = " \\| ";
    private static final String markCheck = "1";
    File file;
    public Storage(String s) {
        this.file = new File(s);
    }
    /**
     * Loads tasks from a file and returns them as a list of tasks.
     *
     * @return a list of tasks loaded from the file. If the file is not found, returns an empty list
     */
    public List<Task> loadFile() {
        try {
            Scanner s = new Scanner(file);
            List<Task> array = new ArrayList<>();
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(splitBy);
                switch (parts[0]) {
                case "T":
                    array.add(new Todo(parts[2], parts[1].equals(markCheck)));
                    break;
                case "D":
                    array.add(new Deadline(parts[2], parts[3], parts[1].equals(markCheck)));
                    break;
                case "E":
                    array.add(new Event(parts[2], parts[3], parts[4], parts[1].equals(markCheck)));
                    break;
                }
            }
            s.close();
            return array;
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
    /**
     * Writes a list of tasks to a file.
     *
     * @param array the list of tasks to be written to the file
     */
    public void writeFile(List<Task> array) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : array) {
                fw.write(task.save() + '\n');
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Creates a new file if it does not already exist.
     *
     */
    public void createFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * Creates a directory ./data
     *
     */
    public void createFolder() {
        File newFolder = new File("./data");
        newFolder.mkdirs();
    }
}
