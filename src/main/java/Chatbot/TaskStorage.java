package Chatbot;

import Tasks.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code TaskStorage} class manages the storage and retrieval of tasks.
 * It utilizes {@code HarddiskStorage} for persistent storage and maintains a map of tasks in memory.
 * The class supports adding, marking, retrieving, deleting, and listing tasks.
 */
public class TaskStorage {
    private final Map<Integer, Task> tasks;
    private final HarddiskStorage harddiskStorage;
    private int autoIncrementId;

    /**
     * Constructs a {@code TaskStorage} object with the specified {@code HarddiskStorage}.
     * The constructor attempts to load tasks from the hard disk. If tasks are found, the map is initialized with them.
     * The auto-incrementing task ID is set to the next available ID based on the loaded tasks.
     *
     * @param harddiskStorage the {@code HarddiskStorage} object used for persistent storage.
     */
    public TaskStorage(HarddiskStorage harddiskStorage) {
        this.harddiskStorage = harddiskStorage;

        // Attempt to load tasks from the hard disk storage
        Map<Integer, Task> loadedTasks = this.harddiskStorage.load();

        // Initialize the tasks map based on the loaded data
        if (loadedTasks != null && !loadedTasks.isEmpty()) {
            tasks = loadedTasks;

            // Find the maximum key to set the autoIncrementId correctly
            autoIncrementId = loadedTasks.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
        } else {
            tasks = new HashMap<>();
            autoIncrementId = 1; // Start the ID from 1
        }
    }

    /**
     * Adds a new task to the storage and persists it to the hard disk.
     * The task is assigned a unique ID that is automatically incremented.
     *
     * @param task the task to be added to the storage.
     */
    public void addTask(Task task) {
        tasks.put(autoIncrementId, task);
        this.harddiskStorage.save(tasks);
        autoIncrementId++;
    }

    /**
     * Marks the task with the specified ID as done and persists the change to the hard disk.
     * If the task ID is invalid, an error message is printed.
     *
     * @param taskId the ID of the task to be marked as done.
     */
    public void markTask(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.markDone();
            this.harddiskStorage.save(tasks);
        } else {
            System.out.println("    Invalid task ID.");
        }
    }

    /**
     * Retrieves the task associated with the specified ID.
     *
     * @param taskId the ID of the task to retrieve.
     * @return the task associated with the specified ID, or {@code null} if no such task exists.
     */
    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    /**
     * Deletes the task with the specified ID from the storage and persists the change to the hard disk.
     *
     * @param taskId the ID of the task to be deleted.
     */
    public void deleteTask(int taskId) {
        tasks.remove(taskId);
        this.harddiskStorage.save(tasks);
    }

    /**
     * Returns the total number of tasks currently stored.
     *
     * @return the number of tasks in the storage.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Prints a list of all tasks currently stored, along with their IDs.
     */
    public void listTasks() {
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            System.out.println("     " + entry.getKey() + ". " + entry.getValue());
        }
    }
}
