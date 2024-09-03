package Alex.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the list by index.
     * @param index The index of the task to delete.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        Task removedTask = tasks.remove(index);
        System.out.println("Deleted task: " + removedTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Retrieves a task from the task list based on the index.
     *
     * @param index The zero-based index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a list of all tasks.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Finds tasks in the task list that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

