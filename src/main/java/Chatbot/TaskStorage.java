package Chatbot;

import Tasks.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code TaskStorage} class manages the storage and retrieval of tasks.
 * It utilizes {@code HarddiskStorage} for persistent storage and maintains a map of tasks in memory.
 * The class supports adding, marking, retrieving, deleting, listing, and finding tasks.
 */
public class TaskStorage {
    private final Map<Integer, Task> tasks;
    private final HarddiskStorage hardDiskStorage;
    private int autoIncrementId;

    /**
     * Constructs a {@code TaskStorage} object with the specified {@code HarddiskStorage}.
     * The constructor attempts to load tasks from the hard disk. If tasks are found, the map is initialized with them.
     * The auto-incrementing task ID is set to the next available ID based on the loaded tasks.
     *
     * @param hardDiskStorage the {@code HarddiskStorage} object used for persistent storage.
     */
    public TaskStorage(HarddiskStorage hardDiskStorage) {
        this.hardDiskStorage = hardDiskStorage;

        // Attempt to load tasks from the hard disk storage
        Map<Integer, Task> loadedTasks = this.hardDiskStorage.load();

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
        this.hardDiskStorage.save(tasks);
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
            this.hardDiskStorage.save(tasks);
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
        this.hardDiskStorage.save(tasks);
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
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            sb.append("     ").append(entry.getKey()).append(". ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Finds and returns a map of tasks that contain the given keyword in their description.
     *
     * @param keyword the keyword to search for in task descriptions.
     * @return a map of tasks where the description contains the keyword.
     */
    public Map<Integer, Task> findTasks(String keyword) {
        Map<Integer, Task> foundTasks = new HashMap<>();
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (entry.getValue().getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.put(entry.getKey(), entry.getValue());
            }
        }
        return foundTasks;
    }
}
