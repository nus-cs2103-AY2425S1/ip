package tudee.task;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, retrieve, and delete tasks from the list.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty TaskList if no parameter specified.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Retrieves the entire list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> get() {
        return taskList;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task delete(int index) {
        return taskList.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }
}