package crack;

import java.util.ArrayList;

/**
 * The TaskList class manages the list of tasks in the application.
 * It provides methods to add, remove, and retrieve tasks, as well as other
 * operations
 * such as checking if the list is empty and listing all tasks.
 */
public class TaskList {
    private ArrayList < Task > tasks;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with a pre-existing list of tasks.
     *
     * @param tasks an ArrayList of tasks to initialize the task list with.
     */
    public TaskList(ArrayList < Task > tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on the given index.
     *
     * @param index the index of the task to be removed (0-based index).
     * @return the task that was removed from the list.
     * @throws IndexOutOfBoundsException if the provided index is out of range.
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Task number out of range.");
        }
    }

    /**
     * Retrieves a task from the task list based on the given index.
     *
     * @param index the index of the task to retrieve (0-based index).
     * @return the task at the specified index.
     * @throws IndexOutOfBoundsException if the provided index is out of range.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("Task number out of range.");
        }
    }

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return the size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a string representation of all tasks in the task list.
     * Each task is listed with its corresponding number (1-based index).
     *
     * @return a string representation of the tasks in the task list.
     */
    public String listTasks() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return taskList.toString();
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return the ArrayList of tasks.
     */
    public ArrayList < Task > getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword the keyword to search for in the task descriptions.
     * @return a list of tasks that match the keyword.
     */
    public ArrayList < Task > findTasks(String keyword) {
        ArrayList < Task > matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
