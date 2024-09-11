package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to manage and manipulate the tasks in the list.
 */

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     *  Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task that has to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from list by its index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task is index is valid, otherwise null.
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        return null;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index if valid, otherwise null.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Finds a task by the given description
     * @param description The description of the task to be found
     * @return The tasks which have the same description as specified.
     */
    public ArrayList<Task> findTasks(String description) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        if (description == null || description.trim().isEmpty()) {
            return matchingTasks;
        } else {
            for (Task task: tasks) {
                if (task.getDescription().toLowerCase().contains(description.toLowerCase())) {
                    matchingTasks.add(task);
                }
            }
        }
        return matchingTasks;
    }
    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}