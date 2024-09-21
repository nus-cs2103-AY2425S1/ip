package jeff;

import java.util.ArrayList;

import jeff.exceptions.JeffException;
import jeff.task.Task;

/**
 * Manages the list of tasks.
 *
 * The TaskList class provides methods to add, delete, and retrieve tasks from the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Updates the task at the specified index in the task list with the provided updated task.
     *
     * @param index The index of the task to be updated
     * @param updatedTask The new task that will replace the existing task at the specified index.
     * @throws JeffException If the index is out of range
     */
    public void updateTask(int index, Task updatedTask) throws JeffException {
        if (index < 0 || index >= tasks.size()) {
            throw new JeffException("The index that you have provided is out of range!");
        }

        tasks.set(index, updatedTask);
    }
}
