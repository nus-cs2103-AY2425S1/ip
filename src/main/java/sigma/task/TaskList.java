package sigma.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks that can be managed (added, deleted, retrieved).
 * Provides methods to manipulate and access the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks the list of tasks to be managed
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list based on its number.
     *
     * @param taskNumber the number of the task to be deleted (1-based index)
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    /**
     * Retrieves a task from the list based on its number.
     *
     * @param taskNumber the number of the task to be retrieved (1-based index)
     * @return the task at the specified position in the list
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Retrieves all tasks in the list.
     *
     * @return an {@code ArrayList} containing all tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }
}
