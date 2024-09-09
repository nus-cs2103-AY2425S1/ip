package max.task;

import max.exception.MaxException;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks, providing methods
 * to add, delete, retrieve, and manage the size of the task list.
 */
public class TaskList {
    private ArrayList<Task> storedTasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param al The ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> al) {
        storedTasks = al;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        storedTasks = new ArrayList<>();
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws MaxException If the index is out of bounds (less than 0 or greater than the size of the list).
     */
    public void deleteTask(int index) throws MaxException {
        if (index >= storedTasks.size() || index < 0) {
            throw new MaxException("This task does not exist! Deletion unsuccessful.");
        }
        storedTasks.remove(index);
    }

    /**
     * Returns the number of tasks currently stored in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return storedTasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void addTask(Task task) {
        storedTasks.add(task);
    }

    /**
     * Retrieves a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return storedTasks.get(index);
    }

    /**
     * Returns the entire list of tasks stored in the TaskList.
     *
     * @return An ArrayList containing all the tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return storedTasks;
    }

    public ArrayList<Task> find(String toFind) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task t : storedTasks) {
            if (t.getDescription().contains(toFind)) {
                filteredTasks.add(t);
            }
        }
        return filteredTasks;
    }
}
