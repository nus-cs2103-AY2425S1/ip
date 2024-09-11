package pandabot.storage;

import java.util.ArrayList;

import pandabot.tasks.Task;

/**
 * Manages a list of tasks, providing methods to add, remove, and retrieve tasks.
 * This class encapsulates the task list and provides various operations to manage the tasks,
 * including adding, removing, checking the size, and printing the tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index the index of the task to remove.
     * @return the task that was removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param index the index of the task to retrieve.
     * @return the task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks.
     */
    public int size() {
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
     * Returns the list of tasks.
     *
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints the list of tasks using the specified Ui.
     * The tasks are printed with their index numbers.
     *
     */
    public String printTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}
