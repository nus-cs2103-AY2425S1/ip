package kobe.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks in the Kobe chatbot application.
 * Provides methods to manipulate tasks in the list, including adding, removing, retrieving tasks,
 * and searching tasks by keywords or tags.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes the task list with an empty ArrayList.
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
     * Adds one or more tasks to the task list.
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
     * Finds tasks that contain the specified keyword in either their description or tags.
     *
     * @param keyword The keyword to search for in task descriptions or tags.
     * @return A TaskList containing the matching tasks.
     */
    public TaskList findTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.name.contains(keyword) || task.hasTag(keyword))
                .collect(Collectors.toList());
        return new TaskList(matchingTasks);
    }

    /**
     * Finds tasks that contain the specified tag.
     *
     * This method filters the tasks in the task list and returns a new TaskList
     * containing only the tasks that have the given tag.
     *
     * @param tag The tag to search for in the tasks.
     * @return A TaskList containing the tasks that have the specified tag.
     */
    public TaskList findTasksByTag(String tag) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.hasTag(tag))
                .collect(Collectors.toList());
        return new TaskList(matchingTasks);
    }

    /**
     * Returns a string representation of all tasks in the task list.
     * The format includes the task number, task details, and tags (if any).
     *
     * @return A formatted string representing all tasks in the task list, or
     *         "Your task list is currently empty." if the list has no tasks.
     */
    public String getAllTasksAsString() {
        if (tasks.isEmpty()) {
            return "Your task list is currently empty.";
        }
        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())
                .collect(Collectors.joining("\n"));
    }
}
