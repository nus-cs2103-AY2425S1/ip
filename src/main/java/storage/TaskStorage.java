package storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import skibidi.SkibidiException;

/**
 * Manages the storage of tasks in a list.
 */
public class TaskStorage {
    private static final int MAX_TASKS = 100;
    private List<Task> tasks;
    private final StorageManager storageManager;

    /**
     * Creates a new TaskStorage.
     *
     * @param filePath The file path to store the tasks.
     * @throws IOException If an I/O error occurs.
     */
    public TaskStorage(String filePath) throws IOException {
        tasks = new ArrayList<>(MAX_TASKS);
        storageManager = new StorageManager(filePath);
        try {
            tasks = storageManager.loadTasks();
        } catch (IOException e) {
            storageManager.createFile();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @throws SkibidiException If the task list is full.
     * @throws IOException If an I/O error occurs.
     */
    public void addTask(Task task) throws SkibidiException, IOException {
        if (tasks.size() >= MAX_TASKS) {
            throw new SkibidiException("Task list is full.");
        }
        tasks.add(task);
        storageManager.saveTasks(tasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to delete.
     * @throws SkibidiException If the task index is out of bounds.
     * @throws IOException If an I/O error occurs.
     */
    public void deleteTask(int index) throws SkibidiException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new SkibidiException("Task index out of bounds.");
        }
        tasks.remove(index);
        storageManager.saveTasks(tasks);
    }

    /**
     * Saves the tasks to the file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks() throws IOException {
        storageManager.saveTasks(tasks);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as done.
     * @param keyword The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
