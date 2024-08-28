package myapp.helperbuddy;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes the task list as an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a Task to the list.
     *
     * @param task The Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a Task from the list at the specified index.
     * If the index is out of bounds (i.e., less than 0 or greater than or equal to the size of the list),
     * this method returns null
     *
     * @param index The index of the Task to retrieve.
     * @return The Task at the specified index, or null if the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Deletes and returns the Task from the list at the specified index.
     * If the index is out of bounds (i.e., less than 0 or greater than or equal to the size of the list),
     * this method may throw an IndexOutOfBoundsException
     *
     * @param index The index of the Task to delete.
     * @return The Task that was removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of Task objects in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of Task objects.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
