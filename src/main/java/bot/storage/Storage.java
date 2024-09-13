package bot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import bot.enums.TaskSymbol;
import bot.exceptions.InvalidTaskEnumException;
import bot.tasks.Deadline;
import bot.tasks.Event;
import bot.tasks.Task;
import bot.tasks.TaskList;
import bot.tasks.Todo;

/**
 * Handles writing to and reading from local files.
 *
 * @author mongj
 */
public class Storage {
    private static final String DIR_PATH = "data";
    private static final String TASK_FILE_PATH = DIR_PATH + "/tasks.txt";

    // TODO: Allow constructor to take in file path
    public Storage() {
        init();
    }

    /**
     * Initialises data directory and an empty data file(s) if they do not exist.
     * If the directory or file(s) cannot be initialised, the program exits.
     */
    private void init() {
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
    public void saveTaskList(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(TASK_FILE_PATH);
        fw.write(tasks.toData());
        fw.close();
    }

    /**
     * Load tasks from data file
     *
     * @param tasks <code>TaskList</code> to add the tasks to.
     * @throws FileNotFoundException If data file is not found.
     */
    public void loadTasks(TaskList tasks) throws FileNotFoundException {
        File f = new File(TASK_FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            try {
                tasks.add(parseData(s.nextLine()));
            } catch (InvalidTaskEnumException e) {
                System.out.println("The current line cannot be read and will be skipped. " + e.getMessage());
            }
        }
    }

    /**
     * Parses string data into a <code>Task</code> object
     *
     * @param data String data read from the local disk file.
     * @return <code>Task</code> object parsed from the given data.
     */
    private Task parseData(String data) throws InvalidTaskEnumException {
        String[] args = data.split(" \\| ");
        // TODO: handle corrupted data
        TaskSymbol sym = TaskSymbol.fromString(args[0]);
        return switch (sym) {
            case TODO -> new Todo(args[1], Boolean.parseBoolean(args[2]));
            case DEADLINE -> new Deadline(args[1], Boolean.parseBoolean(args[2]), LocalDate.parse(args[3]));
            case EVENT -> new Event(
                    args[1],
                    Boolean.parseBoolean(args[2]),
                    LocalDate.parse(args[3]),
                    LocalDate.parse(args[4])
            );
        };
    }
}
