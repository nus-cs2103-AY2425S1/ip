package soju;

import soju.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code TaskList} class represents a list of tasks.
 * It provides methods for adding, deleting, marking, and unmarking tasks,
 * as well as retrieving tasks and the size of the list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a {@code TaskList} with a given list of tasks.
     *
     * @param tasks The list of tasks to initialize the task list with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Deletes a task from the task list by its position.
     *
     * @param taskNumber The position of the task in the list (1-based index).
     * @return The task that was deleted.
     */
    public Task deleteTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add to the list.
     * @return The task that was added.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Marks a task as done by its position in the task list.
     *
     * @param taskNumber The position of the task in the list (1-based index).
     * @return The task that was marked as done.
     */
    public Task markTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks a task (marks it as not done) by its position in the task list.
     *
     * @param taskNumber The position of the task in the list (1-based index).
     * @return The task that was unmarked.
     */
    public Task unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.unmark();
        return task;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
    /**
     * Returns a string representation of the task list.
     * Each task is listed with its position number and description.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + "." + task)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Finds and returns a string representation of tasks that contain the specified keyword in their description.
     * Each matching task is listed with its position number and description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A string representation of matching tasks, where each task is prefixed with its position number.
     *         Returns an empty string if no tasks match the keyword.
     */
    public String findMatchingTasks(String keyword) {
        return tasks.stream().filter(task -> task.getDescription().contains(keyword))
                .map(task -> (tasks.indexOf(task) + 1) + "." + task)
                .collect(Collectors.joining("\n"));
    }

}
