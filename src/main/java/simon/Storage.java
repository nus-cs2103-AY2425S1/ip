package simon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Manages the storage and retrieval of task data for the Simon application.
 * Handles saving tasks to a file and loading tasks from a file.
 */
public class Storage {
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final String filepath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath the path to the file where tasks will be saved and loaded
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    /**
     * Saves the provided list of tasks to the file.
     * Each task is saved in a format suitable for later retrieval.
     *
     * @param taskList the list of tasks to be saved
     */
    public void saveToFile(ArrayList<Task> taskList) {
        try (FileWriter writer = new FileWriter(filepath)) {
            for (Task task : taskList) {
                writer.write(task.toSaveFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }
    /**
     * Loads tasks from the file and returns them as an ArrayList.
     * Creates a new file if it does not exist.
     *
     * @return an ArrayList of tasks loaded from the file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(filepath + " Created");
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        } else {
            try (Scanner sc = new Scanner(file)) {
                taskList = new ArrayList<>(); // Ensure the list is empty before loading
                int taskCount = 0; // Reset task count
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split(" \\| ");
                    Task task = null;

                    switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2], taskCount);
                        break;
                    case "D":
                        // Parse the deadline string into LocalDateTime
                        LocalDateTime deadline = LocalDateTime.parse(parts[3], SAVE_FORMATTER);
                        task = new Deadline(parts[2], taskCount, deadline);
                        break;
                    case "E":
                        task = new Events(parts[2], taskCount, parts[3], parts[4]);
                        break;
                    default:
                        break;
                    }

                    if (task != null) {
                        if (parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        taskList.add(task);
                        taskCount++;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while loading the file: " + e.getMessage());
            } catch (NoSuchElementException | ArrayIndexOutOfBoundsException e) {
                System.out.println("An error occurred while parsing the file: " + e.getMessage());
            }
        }
        return taskList;
    }


}
