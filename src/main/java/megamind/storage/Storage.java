package megamind.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import megamind.task.Task;

/**
 * The `Storage` class handles the saving and loading of tasks to and from a file.
 * It ensures that tasks are persisted between program runs.
 * The class provides methods to save tasks to a file and load tasks from a file.
 */
public class Storage {

    /**
     * Saves tasks to the file.
     * If the file doesn't exist, it is created.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";

        // Create the directory if it doesn't exist
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs(); // Create the directory including any necessary parent directories
        }

        // Save the tasks to the file
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/tasks.ser"));
            oos.writeObject(tasks);
            System.out.println("Tasks have been saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file.
     * If the file doesn't exist, an empty task list is initialized.
     * If loading fails, an empty task list is initialized.
     * If loading is successful, the tasks are loaded into the task list.
     *
     * Warning can be suppressed as the only object type that can be saved
     * is an ArrayList consisting of Task objects.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() {
        // Create the directory if it doesn't exist
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs(); // Create the directory including any necessary parent directories
        }

        // Check if the file exists
        File taskFile = new File(dataDir, "tasks.ser");
        if (!taskFile.exists()) {
            System.out.println("No saved tasks found.");
            return new ArrayList<>(); // Initialize an empty task list if the file doesn't exist
        }

        // Load tasks from the file if it exists
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(taskFile))) {
            System.out.println("Tasks have been loaded successfully.");
            return (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Initialize an empty task list if loading fails
        }
    }
}
