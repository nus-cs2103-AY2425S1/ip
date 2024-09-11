package monobot.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import monobot.exception.MonoBotException;
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
        assert task != null : "Task to be added should not be null";
        int initialSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "Task list size should increase by 1 after adding a task";
    }

    /**
     * Deletes multiple tasks from the list.
     *
     * @param indices List of indices of Tasks in the list to be deleted.
     * @return List of Tasks that were deleted.
     * @throws MonoBotException If any index is invalid.
     */
    public List<Task> deleteTasks(List<Integer> indices) throws MonoBotException {
        List<Task> deletedTasks = new ArrayList<>();
        indices.sort(Comparator.reverseOrder());

        for (int index : indices) {
            if (index < 0 || index >= tasks.size()) {
                throw new MonoBotException("Invalid task index: " + (index + 1));
            }
            Task deletedTask = tasks.remove(index);
            deletedTasks.add(deletedTask);
        }
        return deletedTasks;
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
     * Marks multiple tasks in the list.
     *
     * @param indices List of indices of Tasks in the list to be marked.
     * @return List of Tasks that were marked.
     * @throws MonoBotException If any index is invalid.
     */
    public List<Task> markTasks(List<Integer> indices) throws MonoBotException {
        List<Task> markedTasks = new ArrayList<>();
        for (int index : indices) {
            if (index < 0 || index >= tasks.size()) {
                throw new MonoBotException("Invalid task index: " + (index + 1));
            }
            Task task = tasks.get(index);
            task.markTask();
            markedTasks.add(task);
        }
        return markedTasks;
    }

    /**
     * Unmarks multiple tasks in the list.
     *
     * @param indices List of indices of Tasks in the list to be unmarked.
     * @return List of Tasks that were unmarked.
     * @throws MonoBotException If any index is invalid.
     */
    public List<Task> unmarkTasks(List<Integer> indices) throws MonoBotException {
        List<Task> unmarkedTasks = new ArrayList<>();
        for (int index : indices) {
            if (index < 0 || index >= tasks.size()) {
                throw new MonoBotException("Invalid task index: " + (index + 1));
            }
            Task task = tasks.get(index);
            task.unmarkTask();
            unmarkedTasks.add(task);
        }
        return unmarkedTasks;
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
     * @return ArrayList containing tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Filters tasks based on keyword.
     *
     * @param filters keywords to filter tasks.
     * @return List of tasks containing the keyword.
     */
    public TaskList filterTasks(String... filters) throws MonoBotException {
        assert filters != null && filters.length > 0 : "Filter tasks should be provided with at least one keyword";

        if (filters == null || filters.length == 0) {
            throw new MonoBotException("At least one search keyword must be provided!");
        }

        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(task -> {
                    String taskString = task.toString().toLowerCase();
                    return Arrays.stream(filters)
                            .anyMatch(filter -> taskString.contains(filter.toLowerCase()));
                })
                .collect(Collectors.toCollection(ArrayList::new));

        return new TaskList(filteredTasks);
    }
}
