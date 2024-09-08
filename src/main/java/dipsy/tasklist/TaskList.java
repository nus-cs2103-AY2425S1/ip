package dipsy.tasklist;

import java.util.ArrayList;

import dipsy.storage.Storage;
import dipsy.task.Task;

/**
 * Represents a list of tasks in the task management system.
 * Provides methods for adding, deleting, retrieving, and filtering tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskList} with an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a new {@code TaskList} initialized with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the {@code TaskList} with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the current list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        Storage.save(tasks);
    }

    /**
     * Deletes the task at the specified index from the task list,
     * saves the updated list to storage, and returns the removed task.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed.
     */
    public Task deleteTask(int index) {
        Task removedTask = tasks.remove(index);
        Storage.save(tasks);
        return removedTask;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Filters the task list and returns a new list of tasks that contain the specified keyword
     * in their description. The search is case-insensitive.
     *
     * @param keyword The keyword to filter tasks by.
     * @return A list of tasks that contain the keyword in their description.
     */
    public ArrayList<Task> getTasksByKeyword(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.hasKeywordInDescription(keyword)) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    /**
     * Saves the current task list to local disk using the {@link Storage} class.
     */
    public void saveToLocalDisk() {
        Storage.save(tasks);
    }
}
