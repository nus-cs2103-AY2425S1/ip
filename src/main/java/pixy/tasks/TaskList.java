package pixy.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the list of tasks inputted by the user.
 */
public class TaskList {

    /** List of Tasks */
    private List<Task> tasks;

    /**
     * Creates an empty ArrayList for the tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified tasks list.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of type Task.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param task The task to remove.
     */
    public void remove(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns a List of Tasks which match the description.
     *
     * @param description The description inputted by user.
     * @return List of Tasks having the specified description in their description.
     */
    public List<Task> find(String description) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(description))
                .collect(Collectors.toList());
    }
    /**
     * Returns the task at the specified index of the TaskList.
     *
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the TaskList is empty or not.
     *
     * @return True if task list is empty, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
