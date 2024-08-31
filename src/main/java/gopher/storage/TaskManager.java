package gopher.storage;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.FileCorruptedException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;
import gopher.task.Task;

import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents the storage for managing task related data files.
 */
public class TaskManager {
    /** Path of the task data file relative to the working directory */
    private static final Path taskFile = Paths.get("./task/task.txt");

    /**
     * Converts ArrayList of Task to String data.
     *
     * @param tasks ArrayList of Tasks
     * @return string to be stored in the data file
     */
    private static String readTaskList(ArrayList<Task> tasks) {
        StringBuilder taskString = new StringBuilder();
        for (Task task : tasks) {
            taskString.append(task.getSaveMessage());
            taskString.append("\n");
        }
        return taskString.toString();
    }

    /**
     * Convert String data to ArrayList of Task.
     *
     * @param taskString string data about the tasks
     * @return ArrayList of Task
     * @throws FileCorruptedException if the task String can't be interpreted
     */
    private static ArrayList<Task> convertToTaskList(String taskString)
            throws FileCorruptedException {
        ArrayList<Task> tasks = new ArrayList<>();
        String[] taskRows = taskString.split("\n");
        String[] tokens;
        for (String row : taskRows) {
            if (row.isEmpty()) continue;

            tokens = row.split(" \\| ");
            String taskType = tokens[0];
            String taskStatus = tokens[1];
            String taskName = tokens[2];

            try {
                Task newTask = null;
                switch (taskType) {
                    case "T":
                        newTask = Task.of("todo " + taskName);
                        break;
                    case "D":
                        newTask = Task.of(String.format("deadline %s /by %s",
                                taskName,
                                tokens[3]));
                        break;
                    case "E":
                        newTask = Task.of(String.format("event %s /from %s /to %s",
                                taskName,
                                tokens[3],
                                tokens[4]));
                        break;
                }
                if (newTask != null && taskStatus.equals("X")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            } catch (UnknownCommandException
                     | EmptyTaskDescriptionException
                     | MissingTokenException e) {
                throw new FileCorruptedException("Task File is corrupted...");
            }
        }
        return tasks;
    }

    /**
     * Initialize the Task Manager to set up the required paths and files.
     * Check if the relative path already exists in the user's system.
     * If not, create them.
     */
    public static void initialize() {
        try {
            Files.createDirectories(taskFile.getParent());
            if (!Files.exists(taskFile)) {
                Files.createFile(taskFile);
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
            String taskString = readTaskList(tasks);
            Files.writeString(taskFile, taskString);
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
            String taskString = Files.readString(taskFile);
            return convertToTaskList(taskString);
        } catch (IOException e) {
            System.out.println("Error when loading tasks...");
        } catch (FileCorruptedException e) {
            System.out.println("Load File Failed: Task file corrupted...");
        }
        return new ArrayList<>();
    }
}