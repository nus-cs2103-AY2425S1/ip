package tars;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks and handles operations such as adding, removing,
 * marking tasks as done or undone, and searching for tasks.
 *
 * <p>The TaskList class also interacts with the Storage class to persist the tasks to a file.
 */
public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList with an empty task list and the specified storage.
     *
     * @param storage the Storage object used to save and load tasks.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }


    /**
     * Constructs a TaskList with a pre-existing list of tasks and the specified storage.
     *
     * @param tasks the list of tasks to be managed by this TaskList.
     * @param storage the Storage object used to save and load tasks.
     */
    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds a new task to the task list and saves the updated list to storage.
     *
     * @param task the task to be added.
     * @throws TarsException if there is an error saving the tasks.
     */
    public void addTask(Task task) throws TarsException {
        tasks.add(task);
        saveTasks();
    }

    /**
     * Removes a task from the task list at the specified index and saves the updated list to storage.
     *
     * @param index the index of the task to be removed.
     * @throws TarsException if the specified index is out of bounds or if there is an error saving the tasks.
     */
    public void removeTask(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("The specified task number is out of bounds.");
        }
        tasks.remove(index);
        saveTasks();
    }

    /**
     * Saves the current list of tasks to storage.
     *
     * @throws TarsException if there is an error during the save process.
     */
    private void saveTasks() throws TarsException {
        try {
            storage.saveTasks(this.tasks);
        } catch (TarsException e) {
            throw new TarsException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Marks the task at the specified index as done and saves the updated list to storage.
     *
     * @param index the index of the task to be marked as done.
     * @return the task that was marked as done.
     * @throws TarsException if the specified index is out of bounds or if there is an error saving the tasks.
     */
    public Task markTaskDone(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("The specified task number is out of bounds.");
        }
        Task task = tasks.get(index);
        task.setDone();
        saveTasks();
        return task;
    }

    /**
     * Marks the task at the specified index as not done and saves the updated list to storage.
     *
     * @param index the index of the task to be marked as not done.
     * @return the task that was marked as not done.
     * @throws TarsException if the specified index is out of bounds or if there is an error saving the tasks.
     */
    public Task markTaskUndone(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("The specified task number is out of bounds.");
        }
        Task task = tasks.get(index);
        task.setUndone();
        saveTasks();
        return task;
    }

    /**
     * Returns a formatted string representation of all tasks in the task list.
     *
     * @return a string listing all tasks with their indices.
     */
    public String listTasks() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString().trim();
    }

    /**
     * Finds all tasks in the task list that contain the specified keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of tasks that contain the keyword in their descriptions.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    public int getSize() {
        return tasks.size();
    }

}
