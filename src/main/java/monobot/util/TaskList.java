package monobot.util;

import java.util.ArrayList;
import java.util.stream.Collectors;

import monobot.task.Task;

/**
 * Manages a ArrayList of Task objects.
 * Contains methods to add, delete, mark, unmark and retrieve tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object with an ArrayList of tasks
     *
     * @param tasks ArrayList containing Task objects
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index index of Task in list to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index index of Task in list to get.
     * @return Task to get
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task in the list.
     *
     * @param index index of Task in list to be marked.
     */
    public void markTask(int index) {
        tasks.get(index).markTask();
    }

    /**
     * Unmarks a task in the list.
     *
     * @param index index of Task in list to be unmarked.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmarkTask();
    }

    /**
     * Retrieves number of tasks in the list.
     *
     * @return Number of tasks in list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if number of tasks in the list is 0.
     *
     * @return True if no tasks in list.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Creates new ArrayList of tasks.
     *
     * @return ArrayList containing tasks
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Filters tasks based on keyword.
     *
     * @param filter keyword to filter tasks.
     * @return List of tasks containing the keyword.
     */
    public TaskList filterTasks(String filter) {
        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(task -> task.toString().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(filteredTasks);
    }
}
