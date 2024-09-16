package pandabot.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pandabot.tasks.Task;

/**
 * Handles the loading and saving of tasks from and to a file.
 * This class is responsible for managing the persistence of tasks, allowing them to be stored
 * in a file and retrieved when the application is restarted.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the file specified by filePath.
     * If the file does not exist, an empty task list is returned.
     *
     * @return an ArrayList of Task objects loaded from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public ArrayList<Task> loadTaskList() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    Task task = Task.parse(line.split(" \\| "));
                    if (task != null) {
                        taskList.add(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("There are errors in your Task Data File. Contact the Admin for more info.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the task list to the file specified by filePath.
     * If the directory does not exist, it is created.
     *
     * @param taskList the list of Task objects to save.
     */
    public void saveTaskList(ArrayList<Task> taskList) {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(task.encode() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks: " + e.getMessage());
        }
    }
}
