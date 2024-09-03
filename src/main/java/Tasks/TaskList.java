package Tasks;

import java.util.ArrayList;

/**
 * Class to store {@code Task} objects.
 */
public class TaskList {
    private final ArrayList<Task> TASKS;

    /**
     * Stores the list of tasks.
     * @param tasks {@code ArrayList} of tasks to be stored.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    /**
     * Counts the current number of tasks in the list.
     *
     * @return the current number of tasks.
     */
    public int numTasks() {
        int count = 0;
        for (Task task : TASKS) {
            if (task != null) {
                count += 1;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * Marks a particular task in the list.
     *
     * @param targetIndex Index of the task to be marked.
     */
    public void markTask(int targetIndex) {
        Task task = TASKS.get(targetIndex);
        task.markAsDone();
    }

    /**
     * Unmarks a particular task in the list.
     *
     * @param targetIndex Index of the task to be unmarked.
     */
    public void unmarkTask(int targetIndex) {
        Task targetTask = TASKS.get(targetIndex);
        targetTask.markAsUndone();
    }

    /**
     * Adds a new task into the list.
     *
     * @param task New task to be added.
     */
    public void addToList(Task task) {
        TASKS.add(task);
    }

    /**
     * Removes an existing task from the list.
     *
     * @param targetIndex Index of the task to be removed.
     */
    public void removeFromList(int targetIndex) {
        TASKS.remove(targetIndex);
    }

    /**
     * Gets a particular task from the list.
     *
     * @param targetIndex Index of the task to be retrieved.
     * @return The retrieved task.
     */
    public Task getTask(int targetIndex) {
        return TASKS.get(targetIndex);
    }

    /**
     * Gets the details of a particular task.
     *
     * @param task Task to get details of.
     * @return Task details as a single string.
     */
    public String getTaskDetails(Task task) {
        return "[" + task.getTaskType() + "]"
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getExtraInfo();
    }

    /**
     * Gets the details of a particular task.
     *
     * @param targetIndex Index of the task to get details of.
     * @return Task details as a single string.
     */
    public String getTaskDetails(int targetIndex) {
        Task task = this.getTask(targetIndex);
        return this.getTaskDetails(task);
    }

    /**
     * Gets a summary of the total number of tasks.
     *
     * @return Task summary as a single string.
     */
    public String getTasksSummary() {
        int totalTasks = this.numTasks();
        return "Now you have "
                + totalTasks
                + (totalTasks == 1
                ? " task in the list."
                : " tasks in the list.");
    }
}
