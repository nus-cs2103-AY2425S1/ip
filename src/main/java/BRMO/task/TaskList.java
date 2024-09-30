package BRMO.task;

import java.util.ArrayList;

import BRMO.InvalidCommandException;

/**
 * Represents a list of tasks.
 * Provides methods to add, remove, and retrieve tasks, as well as to get the size of the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified initial list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     * @return the task that was added
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Removes a task at the specified index.
     *
     * @param index the index of the task to be removed
     * @throws InvalidCommandException if the index is out of range
     */
    public void removeTask(int index) throws InvalidCommandException {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new InvalidCommandException("Invalid task index.");
        }
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws InvalidCommandException if the index is out of range
     */
    public Task getTask(int index) throws InvalidCommandException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new InvalidCommandException("Invalid task index.");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /* Finds all tasks that contain the specified substring.
     *
     * @param substring the substring to search for.
     * @return the list of tasks that contain the substring.
     */
    public TaskList find(String substring) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.contains(substring)) {
                matchingTasks.add(task);
            }
        }

        return new TaskList(matchingTasks);
    }

    /**
     * Returns a string representation of the task list, where each task is represented on a new line,
     * with its index (1-based) and the task details.
     *
     * @return a string representation of the task list
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); ++i) {
            res += (i + 1) + "." + tasks.get(i);
            if (i < tasks.size() - 1) {
                res += "\n";
            }
        }
        return res;
    }
}
