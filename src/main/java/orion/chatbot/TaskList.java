package orion.chatbot;

import orion.exceptions.OrionInputException;
import orion.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the chatbot.
 * Provides methods to add, mark, unmark, and delete tasks,
 * as well as to retrieve task descriptions and saved task representations.
 */
public class TaskList {

    private final List<Task> tasks;
    private int noTasks;

    /**
     * Constructs an empty TaskList.
     */
    protected TaskList() {
        tasks = new ArrayList<>();
        noTasks = 0;
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
        noTasks = tasks.size();
    }

    /**
     * Returns a list of formatted task descriptions.
     * Each task is prefixed with its number in the list.
     *
     * @return A list of task descriptions.
     */
    public List<String> getTaskDescriptions() {
        List<String> taskDescriptions = new ArrayList<>();
        for (int i = 0; i < noTasks; i++) {
            String task = String.format("%d. %s", i + 1, tasks.get(i));
            taskDescriptions.add(task);
        }
        return taskDescriptions;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getNoTasks() {
        return noTasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        noTasks++;
    }

    /**
     * Marks a task as done.
     *
     * @param taskNo The index of the task to mark as done.
     * @return The task that was marked.
     * @throws OrionInputException If the task number is invalid.
     */
    public Task markTask(int taskNo) throws OrionInputException {
        Task task = tasks.get(taskNo);
        task.setDone();
        return task;
    }

    /**
     * Unmarks a task as undone.
     *
     * @param taskNo The index of the task to unmark.
     * @return The task that was unmarked.
     * @throws OrionInputException If the task number is invalid.
     */
    public Task unmarkTask(int taskNo) throws OrionInputException {
        Task task = tasks.get(taskNo);
        task.setUndone();
        return task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNo The index of the task to delete.
     * @return The task that was deleted.
     */
    public Task deleteTask(int taskNo) {
        Task task = tasks.get(taskNo);
        tasks.remove(task);
        noTasks--;
        return task;
    }

    /**
     * Returns a list of saved task descriptions.
     * Each task is represented in a format suitable for saving to a file.
     *
     * @return A list of saved task descriptions.
     */
    public List<String> getSavedTaskDescriptions() {
        List<String> savedTaskDescriptions = new ArrayList<>();
        for (Task task : tasks) {
            savedTaskDescriptions.add(task.saveString() + "\n");
        }
        return savedTaskDescriptions;
    }
}