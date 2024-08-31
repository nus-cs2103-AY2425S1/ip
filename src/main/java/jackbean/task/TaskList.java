package jackbean.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the JackBean chatbot.
 * This JavaDoc was written by GitHub Copilot.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Constructs a task list.
     * This JavaDoc was written by GitHub Copilot.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks in the task list.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @return The number of tasks in the task list.
     */
    public String howManyTasks() {
        return "Homie, you have " + taskList.size() + " task(s) in the list now.";
    }

    /**
     * Adds a task to the task list.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param taskNumber The number of the task to be removed.
     */
    public void removeTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    /**
     * Marks a task in the task list as done.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param taskNumber The number of the task to be marked as done.
     */
    public void markTaskAsDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
    }

    /**
     * Returns the number of tasks in the task list.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param taskNumber The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Marks a task in the task list as undone.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param taskNumber The number of the task to be marked as undone.
     */
    public void markTaskAsUndone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsUndone();
    }
}
