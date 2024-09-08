package deez;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import deez.tasks.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Serializable {

    private static final long serialVersionUID = 1L;
    protected ArrayList<Task> tasks;

    /**
     * Constructs a new task list with the given tasks.
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Gets all tasks in the task list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks containing a given keyword and returns them.
     *
     * @param keyword
     * @return A list of tasks that contain the given keyword in their descriptions.
     */
    public List<Task> getTasks(String keyword) {
        return tasks.stream()
            .filter(task -> task.getDescription().contains(keyword))
            .collect(Collectors.toList());
    }

    /**
     * Gets a task at the given index.
     *
     * @param i
     * @return The task at the given index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task at the given index from the task list.
     *
     * @param index
     */
    public void remove(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param t
     */
    public void remove(Task t) {
        this.tasks.remove(t);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks == null || tasks.isEmpty();
    }

    /**
     * Gets the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks == null ? 0 : tasks.size();
    }
}
