package myapp.helperbuddy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList class helps to manage the tasks present inside the task list
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes the task list as an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a Task to the list.
     * @param task The Task to be added to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added should not be null.";
        tasks.add(task);
    }

    /**
     * Retrieves a Task from the list at the specified index.
     * If the index is out of bounds (i.e., less than 0 or greater than or equal to the size of the list),
     * this method returns null
     * @param index The index of the Task to retrieve.
     * @return The Task at the specified index, or null if the index is out of bounds.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within the bounds of the list.";
        return (index >= 0 && index < tasks.size()) ? tasks.get(index) : null;
    }

    /**
     * Deletes and returns the Task from the list at the specified index.
     * If the index is out of bounds (i.e., less than 0 or greater than or equal to the size of the list),
     * this method may throw an IndexOutOfBoundsException
     * @param index The index of the Task to delete.
     * @return The Task that was removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within the bounds of the list.";
        return tasks.remove(index);
    }

    /**
     * Returns the number of Task objects in the list.
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of Task objects.
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks that contain the specified keyword in their descriptions.
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> searchTasks(String keyword) {
        assert keyword != null : "Search keyword should not be null.";
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
