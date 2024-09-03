package Alex.Task;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
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
     * Adds a task to the list.
     * @param task The task to add.
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
     * Gets the task at the specified index.
     * @param index The index of the task to get.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets all tasks in the list.
     * @return An ArrayList of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}

