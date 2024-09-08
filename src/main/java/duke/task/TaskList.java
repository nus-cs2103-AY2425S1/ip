package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * This class provides methods to manage tasks in a list, including adding, removing, and retrieving tasks.
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
     * @param tasks The initial list of tasks to populate the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList by its index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed from the list.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}


