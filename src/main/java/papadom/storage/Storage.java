package papadom.storage;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import papadom.tasks.Task;
import papadom.utils.Parser;

/**
 * Manages the storage of tasks in a file.
 */
public class Storage {
    protected final String FILE_PATH;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file used for storage.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "File path cannot be empty";
        this.FILE_PATH = filePath;
    }

    // Load all tasks from the file
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(this.FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.retrieveTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            createFileIfNotPresent(); // Create the file if it doesn't exist
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Adds a task to the storage database by writing it to the file.
     *
     * @param task The task to be added to the file.
     */
    public void addTaskToDatabase(Task task) {
        try {
            assert task != null : "Task cannot be null";
            FileWriter fw = new FileWriter(this.FILE_PATH, true);
            fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Creates the storage file if it does not exist, including any necessary directories.
     */
    public void createFileIfNotPresent() {
        File file = new File(FILE_PATH);
        try {
            // Check if the file exists, and if not, create it
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    public String findTaskBySearching(String searchText) {
        List<Task> tasks = loadTasks(); // Load tasks from the file
        String searchResult = " Here are the matching tasks in your list:";
        int count = 1;

        for (Task task : tasks) {
            // Perform operations with each task
            String description = task.getDescription();
            if (description.contains(searchText)) {
                searchResult += "\n  " + count++ + ". " + task.toString();
            }
        }

        // Check if no tasks were found
        if (count == 1) {
            searchResult = " No matching tasks found in your list.";
        }

        return searchResult;
    }

    public void clearTasks() {
        try {
            new FileWriter(this.FILE_PATH, false).close(); // Open the file in write mode, which clears it
        } catch (IOException e) {
            System.err.println("An error occurred while clearing tasks: " + e.getMessage());
        }
    }

    /**
     * Updates the tasks.txt file with the current list of tasks.
     */
    public void updateTasksInFile(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.FILE_PATH))) {
            for (Task task : taskList.TASK_LIST) {
                writer.write(task.toString());
                writer.newLine(); // Write each task in a new line
            }
        } catch (IOException e) {
            System.err.println("An error occurred while updating the tasks file: " + e.getMessage());
        }
    }
}
