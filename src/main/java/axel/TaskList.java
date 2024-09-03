package axel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 * Provides methods to manage tasks, including adding, removing, and retrieving tasks.
 */
public class TaskList {
    /** The list of tasks. */
    protected List<Task> tasks;
<<<<<<< HEAD
    /**
     * Constructs a {@code TaskList} with the specified initial list of tasks.
     *
     * @param tasks An initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Removes the task at the specified index from the task list.
     * If the index is invalid, no action is taken.
     *
     * @param index The index of the task to be removed
     */
=======

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

>>>>>>> branch-A-CodingStandard
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
<<<<<<< HEAD
    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve
     * @return The task at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
    /**
     * Returns a list of all tasks in the task list.
     *
     * @return A list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }
    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks
     */
=======

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

>>>>>>> branch-A-CodingStandard
    public int size() {
        return tasks.size();
    }

    public List<Task> findTasksWithKeyword(String keyword) {
        return tasks.stream()
                .filter(task -> task.getTaskName().contains(keyword))
                .collect(Collectors.toList());
    }

}
