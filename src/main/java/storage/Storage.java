package storage;

import task.Task;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import task.Todo;
import task.Deadline;
import task.Event;
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
     * @return a list of tasks loaded from the file. If the file is not found, returns an empty list.
     */
    public List<Task> loadFile() {
        try {
            assert file.exists():"file does not exist";
            Scanner s = new Scanner(file);
            List<Task> array = new ArrayList<>();
            while (s.hasNext()) {
                loadFileContent(s, array);
            }
            s.close();
            return array;
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
    /**
     * Parses a single line from the file and converts it into a Task object.
     *
     * This method processes the given line, splits it by the specified delimiter,
     * and based on the type of task (Todo, Deadline, or Event), adds the task
     * to the provided list. Each task is also marked as complete or incomplete
     * based on the parsed data.
     *
     * @param s the Scanner object used to read the file line by line.
     * @param array the list of tasks to which the parsed tasks will be added.
     */
    public static void loadFileContent(Scanner s, List<Task> array) {
        String line = s.nextLine();
        String[] parts = line.split(splitBy);
        switch (parts[0]) {
            case "T":
                assert parts.length == 3: "wrong length";
                array.add(new Todo(parts[2], parts[1].equals(markCheck)));
                break;
            case "D":
                assert parts.length == 4: "wrong length";
                array.add(new Deadline(parts[2], parts[3], parts[1].equals(markCheck)));
                break;
            case "E":
                assert parts.length == 5: "wrong length";
                array.add(new Event(parts[2], parts[3], parts[4], parts[1].equals(markCheck)));
                break;
        }
    }
    /**
     * Writes a list of tasks to a file.
     *
     * @param array the list of tasks to be written to the file.
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
