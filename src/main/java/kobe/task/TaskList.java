package kobe.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the Kobe chatbot application.
 * Provides methods to manipulate tasks in the list.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds multiple tasks to the task list using varargs.
     *
     * @param tasksToAdd The tasks to be added.
     */
    public void addTask(Task... tasksToAdd) {
        for (Task t : tasksToAdd) {
            tasks.add(t);
        }
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index in the task list.
     *
     * @param index The index of the task to return.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing the matching tasks.
     */
    public TaskList findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.name.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Returns all tasks as a string.
     *
     * @return The string representation of all tasks.
     */
    public String getAllTasksAsString() {
        StringBuilder taskListBuilder = new StringBuilder();
        if (tasks.isEmpty()) {
            return "Your task list is currently empty.";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                taskListBuilder.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
            }
            return taskListBuilder.toString().trim();
        }
    }
}
