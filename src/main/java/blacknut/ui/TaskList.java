package blacknut.ui;

import blacknut.ui.BlacknutExceptions.*;
import java.util.ArrayList;


/**
 * The TaskList class manages the list of tasks.
 */
class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public Task deleteTask(int index) throws BlacknutExceptions.InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new BlacknutExceptions.InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks a task as done or not done.
     *
     * @param index The index of the task to be marked.
     * @param markAsDone True to mark the task as done, false to mark it as not done.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public void markTask(int index, boolean markAsDone) throws BlacknutExceptions.InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new BlacknutExceptions.InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
        }
        if (markAsDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsNotDone();
        }
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public Task getTask(int index) throws BlacknutExceptions.InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new BlacknutExceptions.InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
        }
        return tasks.get(index);
    }

    /**
     * Retrieves the list of tasks.
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
    public int size() {
        return tasks.size();
    }
}