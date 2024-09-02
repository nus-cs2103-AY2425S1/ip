/**
 * Represents the list of tasks inputted by the user.
 */
package pixy.tasks;

import java.util.ArrayList;
import java.util.List;

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

    public List<Task> find(String description) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
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
