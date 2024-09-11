package tudee.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * Provides methods to add, retrieve, and delete tasks from the list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Constructs an empty TaskList if no parameter specified.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the entire list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int numOfTasks() {
        return tasks.size();
    }
}
