package barney.data;

import java.util.ArrayList;
import java.util.stream.Collectors;

import barney.data.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task pop(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a string representation of the TaskList.
     *
     * @return A string representation of the TaskList.
     */
    public String toStringList() {
        if (tasks.isEmpty()) {
            return "No tasks in the list!";
        }
        return tasks.stream()
            .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())
            .collect(Collectors.joining("\n"));
    }

    /**
     * Returns the ArrayList of tasks in the TaskList.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    /**
     * Returns a TaskList containing tasks that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing tasks that match the given keyword.
     */
    public TaskList find(String keyword) {
        return new TaskList(tasks.stream()
            .filter(task -> task.getDescription().contains(keyword))
            .collect(Collectors.toCollection(ArrayList::new)));
    }

}
