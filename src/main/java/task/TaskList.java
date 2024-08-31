package task;

import exceptions.TaskIndexOutOfBound;
import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 * It provides methods to add, delete, mark, unmark, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
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
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws TaskIndexOutOfBound if the index is out of range (index < 0 || index >= tasks.size()).
     */
    public void deleteTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index The index of the task to be marked as done.
     * @throws TaskIndexOutOfBound if the index is out of range (index < 0 || index >= tasks.size()).
     */
    public void markTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.get(index).setMarkStatus(true);
    }

    /**
     * Unmarks a task as not done at the specified index.
     *
     * @param index The index of the task to be unmarked.
     * @throws TaskIndexOutOfBound if the index is out of range (index < 0 || index >= tasks.size()).
     */
    public void unmarkTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.get(index).setMarkStatus(false);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws TaskIndexOutOfBound if the index is out of range (index < 0 || index >= tasks.size()).
     */
    public Task getTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        return tasks.get(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
