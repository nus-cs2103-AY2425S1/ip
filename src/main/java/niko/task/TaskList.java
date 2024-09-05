package niko.task;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a list of tasks.
 * This class provides methods to add, retrieve, find, and manage tasks within the list.
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks in the list that match the given description.
     *
     * @param description The description to match.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> findTasks(String description) {
        ArrayList<Task> findTasks = new ArrayList<>();
        for (Task task : tasks) {
            String info = task.getDescription();
            String[] parts = info.split(" ");
            if ((parts.length > 0 && parts[0].equals(description))
                    || (parts.length > 1 && parts[1].equals(description))) {
                findTasks.add(task);
            }
        }
        System.out.println("Search complete. Number of matching tasks found: " + findTasks.size());
        return findTasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("index is out of bounds.");
        }
    }

    /**
     * Returns the last task in the task list.
     *
     * @return The last task in the task list.
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTaskAsDone(int index) {
        Task task = getTask(index);
        task.markAsDone();
    }

    /**
     * Sets the date for the last task in the task list.
     *
     * @param date The date to be set for the last task.
     */
    public void setDate(Date date) {
        Task task = getLastTask();
        task.setDate(date);
    }

    /**
     * Returns the date of the last task in the task list.
     *
     * @return The date of the last task.
     */
    public Date getDate() {
        Task task = getLastTask();
        return task.getDate();
    }

    /**
     * Unmarks the task at the specified index as done.
     *
     * @param index The index of the task to unmark as done.
     */
    public void unmarkTaskAsDone(int index) {
        Task task = getTask(index);
        task.unmarkAsDone();
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        Task task = getTask(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }
}
