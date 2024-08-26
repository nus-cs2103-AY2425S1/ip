package papadom.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import papadom.tasks.Task;

public class Storage {
    private final String filePath;
    private final ArrayList<Task> TASKS = new ArrayList<>();
    public ArrayList<Task> getTasks() {
        return this.TASKS;
    }
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public void addTaskToDatabase(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public void createFileIfNotPresent() {
        File file = new File(filePath);
        try {
            // Check if the file exists
            if (!file.exists()) {
                // If the file doesn't exist, create it along with any necessary directories
                file.getParentFile().mkdirs(); // Create directories if they don't exist
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
    public String findTaskBySearching (String searchText) {
        String searchResult = " Here are the matching tasks in your list:";
        int count = 1;
        for (Task task : this.TASKS) {
            // Perform operations with each task
            String description = task.getDescription();
            if (description.contains(searchText)) {
                searchResult += "\n  " + count++ + ". " + task.toString();
            }
        }
        return searchResult;
    }
}
