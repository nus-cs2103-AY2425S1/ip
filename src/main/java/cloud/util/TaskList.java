package cloud.util;

import java.util.ArrayList;

import cloud.task.Task;


/**
 * Manages a list of tasks.
 * Allows adding, removing, marking, and finding of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task The task object to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param taskId The index of the task
     * @return The deleted Task
     */
    public Task delete(int taskId) {
        Task deletedTask = this.tasks.remove(taskId - 1);
        return deletedTask;
    }

    /**
     * Marks a task as done.
     *
     * @param taskId Index of the task to be marked
     */
    public void mark(int taskId) {
        tasks.get(taskId - 1).markDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param taskId Index of the task to be unmarked
     */
    public void unmark(int taskId) {
        tasks.get(taskId - 1).markNotDone();
    }

    /**
     * Gets the current status of a task.
     *
     * @param taskId index of the task
     * @return a string representation of the task
     */
    public String getTaskStatus(int taskId) {
        return tasks.get(taskId - 1).toString();
    }

    /**
     * Gets the status of the last added task.
     *
     * @return String representation of the task
     */
    public String getLatestTask() {
        if (tasks.isEmpty()) {
            return "";
        }
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Gets the total number of tasks.
     *
     * @return Count of all marked and unmarked tasks
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Gets a task from the list.
     *
     * @param index index of the task
     * @return the requested task object
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for
     * @return A TaskList containing the found tasks
     */
    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (taskContainsKeyword(task, keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Task task : tasks) {
            sb.append(formatTaskWithIndex(index++, task));
        }
        return sb.toString();
    }

    /**
     * Checks if a task's description contains the keyword.
     *
     * @param task The task to check
     * @param keyword The keyword to search for
     * @return True if the task description contains the keyword
     */
    private boolean taskContainsKeyword(Task task, String keyword) {
        return task.getDescription().contains(keyword);
    }

    /**
     * Formats a task with its index.
     *
     * @param index The index of the task in the list (indexed from 1)
     * @param task The task to be formatted
     * @return A formatted string for the task with its index
     */
    private String formatTaskWithIndex(int index, Task task) {
        return String.format("%d: %s\n", index, task.toString());
    }
}
