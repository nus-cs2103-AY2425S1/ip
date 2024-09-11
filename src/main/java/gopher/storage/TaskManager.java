package gopher.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import gopher.exception.FileCorruptedException;
import gopher.parser.Parser;
import gopher.task.Task;

/**
 * Represents the storage for managing task related data files.
 */
public class TaskManager {
    /** Path of the task data file relative to the working directory */
    private static final Path TASK_FILE = Paths.get("./task/task.txt");

    /**
     * Converts ArrayList of Task to String data.
     *
     * @param tasks ArrayList of Tasks
     * @return string to be stored in the data file
     */
    private static String convertToTaskString(ArrayList<Task> tasks) {
        StringBuilder taskString = new StringBuilder();
        for (Task task : tasks) {
            taskString.append(task.getSaveMessage());
            taskString.append("\n");
        }
        return taskString.toString();
    }

    /**
     * Initialize the Task Manager to set up the required paths and files.
     * Check if the relative path already exists in the user's system.
     * If not, create them.
     */
    public static void initialize() {
        try {
            Files.createDirectories(TASK_FILE.getParent());
            if (!Files.exists(TASK_FILE)) {
                Files.createFile(TASK_FILE);
            }
        } catch (IOException e) {
            System.out.println("Error when creating task file...");
        }
    }

    /**
     * Save the tasks into the local task data file.
     *
     * @param tasks ArrayList of Task tracked by the TaskManager
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            String taskString = convertToTaskString(tasks);
            Files.writeString(TASK_FILE, taskString);
        } catch (IOException e) {
            System.out.println("Error when saving tasks...");
        }
    }

    /**
     * Load the tasks from the local task data file.
     *
     * @return ArrayList of Task stored in the file
     */
    public static ArrayList<Task> loadTasks() {
        try {
            String taskString = Files.readString(TASK_FILE);
            return Parser.parseSavedTaskData(taskString);
        } catch (IOException e) {
            System.out.println("Error when loading tasks...");
        } catch (FileCorruptedException e) {
            System.out.println("Load File Failed: Task file corrupted...");
        }
        return new ArrayList<>();
    }
}
