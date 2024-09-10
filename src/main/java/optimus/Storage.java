package optimus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import optimus.exceptions.InvalidDateFormatException;
import optimus.tasks.DeadlineTask;
import optimus.tasks.EventTask;
import optimus.tasks.Task;
import optimus.tasks.ToDoTask;

/**
 * Handles permanent storage
 */
public class Storage {

    public static final String SPECIAL_CHAR = "%%";
    private static final Path WORKING_DIR = Paths.get(System.getProperty("user.dir"));
    private static final Path DATA_DIR = WORKING_DIR.resolve("data");
    private static final Path STORE_FILE = DATA_DIR.resolve("store.txt");

    public Storage() {
        validateStorageFile();
    }

    private void validateStorageFile() {
        try {
            if (Files.notExists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }

            if (Files.notExists(STORE_FILE)) {
                Files.createFile(STORE_FILE);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Adds a task in string format to the end of permanent storage
     *
     * @param stringToAdd - task in string format
     */
    public void appendToStorage(String stringToAdd) {
        assert Files.exists(STORE_FILE);
        try {
            Files.write(STORE_FILE, List.of(stringToAdd), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Rewrites the entire permanent storage file using data from session storage
     *
     * @param taskList - session storage
     */
    public void rewriteEntireFile(ArrayList<Task> taskList) {
        assert Files.exists(STORE_FILE);
        try {
            List<String> lines = taskList.stream()
                    .map(Task::getStorageString)
                    .toList();
            Files.write(STORE_FILE, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("An error occurred while rewriting the file: " + e.getMessage());
        }
    }

    /**
     * Initialises an ArrayList of Task objects from the tasks in permanent storage to be used in session storage.
     * Used when first starting Optimus
     *
     * @return - An ArrayList containing all the Tasks in permanent storage
     */
    public ArrayList<Task> load() {
        assert Files.exists(STORE_FILE);
        ArrayList<Task> data = new ArrayList<>();
        try {
            List<String> fileLines = Files.readAllLines(STORE_FILE);
            if (fileLines.isEmpty()) {
                return data;
            }
            for (String line : fileLines) {
                String[] values = line.split(SPECIAL_CHAR);
                String type = values[0];
                switch (type) {
                case "T" -> {
                    data.add(new ToDoTask(values[2], values[1]));
                    break;
                }
                case "D" -> {
                    data.add(new DeadlineTask(values[2], values[3], values[1]));
                    break;
                }
                case "E" -> {
                    data.add(new EventTask(values[2], values[3], values[4], values[1]));
                    break;
                }
                default -> {
                    continue;
                }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        } catch (InvalidDateFormatException e) {
            System.out.println("The data stored in the storage file is outdated and incompatible. "
                    + "Please delete store.txt and try again");
        }
        return data;
    }
}
