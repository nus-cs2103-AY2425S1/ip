package julie.misc;

import julie.task.Deadline;
import julie.task.Event;
import julie.task.Task;
import julie.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public static final String DATA_PATH = "./data/julie.txt";
    public static File data = new File(DATA_PATH);
    public static void start() {
        try {
            File directory = new File(data.getParent());
            if (!directory.exists()) {
                directory.mkdirs();  // Create directory if it does not exist
            }
            if (!data.exists()) {
                data.createNewFile();  // Create file if it does not exist
            }
        } catch (IOException e) {
            System.out.println("Error initializing file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks present in the document at the given data path.
     * Format of lines is T | Desc | From | To.
     * @param taskList
     */
    public static void load(List<Task> taskList) {
        try {
            Scanner sc = new Scanner(data);
            while (sc.hasNext()) {
                String s = sc.nextLine();
                String[] tokens = s.split(" \\| ");
                String cmd = tokens[0];
                Task t = null; // Variable
                if (cmd.equals("T")) {
                    t = new ToDo(tokens[1]);
                } else if (cmd.equals("D")) {
                    t = new Deadline(tokens[1], LocalDate.parse(tokens[2]));
                } else if (cmd.equals("E")) {
                    t = new Event(tokens[1], LocalDate.parse(tokens[2]), LocalDate.parse(tokens[3]));
                }
                taskList.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found"); // Should not reach this block
        }
    }

    public static void save(Task t) {
        try {
            FileWriter fw = new FileWriter(DATA_PATH, true);
            fw.write(t.toStorageString() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found"); // Should not reach this block
        }
    }
}
