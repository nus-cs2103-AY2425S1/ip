package tasks;

import storage.Storage;
import exceptions.JarException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks. The TaskList class manages the addition, removal, and modification of tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
    }

    /**
     * Constructs a TaskList by loading tasks from the provided storage.
     *
     * @param storage The storage from which tasks are loaded.
     * @throws IOException If an I/O error occurs while loading tasks.
     * @throws JarException If the data in the storage is corrupted.
     */
    public TaskList(Storage storage) throws IOException, JarException {
        this.tasks = new ArrayList<>(storage.load());  // Load tasks from the storage
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     * @throws JarException If the task is null.
     */
    public void addTask(Task task) throws JarException {
        if (task == null) {
            throw new JarException("Cannot add a null task.");
        }
        this.tasks.add(task);
    }

    /**
     * Returns a string representation of all tasks in the TaskList.
     *
     * @return A string listing all tasks, or a message indicating that there are no tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No Tasks added yet, pls add tasks!";
        }
        StringBuilder taskListString = new StringBuilder();
        int counter = 1;
        for (Task task : tasks) {
            taskListString.append(counter).append(". ").append(task.toString()).append("\n");
            counter++;
        }
        return taskListString.toString();
    }

    /**
     * Marks a task as completed based on its index in the TaskList.
     *
     * @param index The index of the task to be marked as completed.
     * @throws JarException If the index is invalid.
     */
    public void markTaskAsDone(int index) throws JarException {
        Task task = getTask(index);
        task.setStatus(true);
    }

    /**
     * Marks a task as not completed based on its index in the TaskList.
     *
     * @param index The index of the task to be marked as not completed.
     * @throws JarException If the index is invalid.
     */
    public void markTaskAsUndone(int index) throws JarException {
        Task task = getTask(index);
        task.setStatus(false);
    }

    /**
     * Retrieves a task from the TaskList based on its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws JarException If the index is invalid.
     */
    public Task getTask(int index) throws JarException {
        if (index < 0 || index >= tasks.size()) {
            throw new JarException("Invalid task number!");
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param index The index of the task to be deleted.
     * @throws JarException If the index is invalid.
     */
    public void deleteTask(int index) throws JarException {
        if (index < 0 || index >= tasks.size()) {
            throw new JarException("Invalid task number, cannot delete task");
        }
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public ArrayList<Task> findTasks(String keyword) throws JarException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new JarException("Task cannot be empty!");
        } else {
            ArrayList<Task> sameTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.toString().contains(keyword)) {
                    sameTasks.add(task);
                }
            }
            return sameTasks;
        }
    }
}
