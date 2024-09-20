package orion.utils;

import java.util.ArrayList;
import java.util.List;

import orion.exceptions.OrionInputException;
import orion.tasks.Task;

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
    public TaskList() {
        tasks = new ArrayList<>();
        noTasks = 0;
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "tasks cannot be null";
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
        assert tasks != null : "tasks cannot be null";
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
        assert task != null : "task cannot be null";
        tasks.add(task);
        noTasks++;
    }

    /**
     * Marks a task as done.
     *
     * @param taskNo The index of the task to mark as done.
     * @return The task that was marked.
     * @throws OrionInputException If the task is already marked as completed.
     */
    public Task markTask(int taskNo) throws OrionInputException {
        assert taskNo >= 0 && taskNo < getNoTasks() : "invalid task number";
        Task task = tasks.get(taskNo);
        task.setDone();
        return task;
    }

    /**
     * Unmarks a task as undone.
     *
     * @param taskNo The index of the task to unmark.
     * @return The task that was unmarked.
     * @throws OrionInputException If the task is already marked as uncompleted.
     */
    public Task unmarkTask(int taskNo) throws OrionInputException {
        assert taskNo >= 0 && taskNo < getNoTasks() : "invalid task number";
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
        assert taskNo >= 0 && taskNo < getNoTasks() : "invalid task number";
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
        assert tasks != null : "tasks cannot be null";
        List<String> savedTaskDescriptions = new ArrayList<>();
        for (Task task : tasks) {
            savedTaskDescriptions.add(task.saveString() + "\n");
        }
        return savedTaskDescriptions;
    }

    /**
     * Retrieves a list of tasks that contain the specified keyword in their body.
     * The search is case-insensitive.
     *
     * @param keyword the keyword to search for within the tasks' bodies
     * @return a list of {@link Task} objects whose body contains the keyword
     */
    public List<String> getMatchingTasks(String keyword) {
        assert tasks != null : "tasks cannot be null";
        assert keyword != null : "keyword cannoy be null";
        List<String> matchingTasks = new ArrayList<>();
        int counter = 0;
        for (Task task : this.tasks) {
            if (task.getBody().toLowerCase().contains(keyword.toLowerCase())) {
                String taskDescription = String.format("%d. %s", ++counter, task);
                matchingTasks.add(taskDescription);
            }
        }
        return matchingTasks;
    }
}
