package bot.storage;

import bot.tasks.Deadline;
import bot.tasks.Event;
import bot.tasks.Task;
import bot.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles writing to and reading from local files.
 *
 * @author mongj
 */
public class Storage {
    private static final String DIR_PATH = "data";
    private static final String TASK_FILE_PATH = DIR_PATH + "/tasks.txt";

    /**
     * Initialises data directory and an empty data file(s) if they do not exist.
     * If the directory or file(s) cannot be initialised, the program exits.
     */
    public void init() {
        File d = new File(DIR_PATH);
        if (!d.exists() || !d.isDirectory()) {
            // Initialize directory
            boolean ok = d.mkdir();
            if (!ok) {
                System.out.println("Failed to initialize storage directory");
                System.exit(0);
            }
        }

        // TODO: Abstract storage of each type into its own class
        // Initialize task storage
        File f = new File(TASK_FILE_PATH);
        // TODO: Validate file content to make sure it is not corrupted
        if (!f.exists()) {
            try {
                boolean ok = f.createNewFile();
                if (!ok) {
                    System.out.println("Failed to initialize storage file at " + TASK_FILE_PATH);
                    System.exit(0);
                }
            } catch (IOException e) {
                System.out.println("Error initialising storage file at " + TASK_FILE_PATH);
                System.exit(0);
            }
        }
    }

    /**
     * Saves the tasks to the data file.
     *
     * @param tasks List of tasks to save.
     * @throws IOException If file cannot be saved.
     */
    public void saveTaskList(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(TASK_FILE_PATH);
        for (Task t : tasks) {
            fw.write(t.toData() + "\n");
        }
        fw.close();
    }

    /**
     * Load tasks from data file
     *
     * @return ArrayList of tasks
     * @throws FileNotFoundException If data file is not found.
     */
    public List<Task> loadTasks() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(TASK_FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            tasks.add(parseData(s.nextLine()));
        }
        return tasks;
    }

    private Task parseData(String data) {
        String[] args = data.split(" \\| ");
        // TODO: handle corrupted data
        return switch (args[0]) {
            // TODO: Replace with enums
            case "T" -> new Todo(args[1], Boolean.parseBoolean(args[2]));
            case "D" -> new Deadline(args[1], Boolean.parseBoolean(args[2]), args[3]);
            case "E" -> new Event(args[1], Boolean.parseBoolean(args[2]), args[3], args[4]);
            default -> null;
        };
    }
}
