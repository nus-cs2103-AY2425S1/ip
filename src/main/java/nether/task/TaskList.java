package nether.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * The {@code TaskList} class provides methods to manage a collection of {@code Task} objects,
 * such as adding, deleting, and retrieving tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an initial list of tasks.
     * This method is used when we are loading tasks from data file.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a {@code Task} to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a {@code Task} from the task list by its index.
     *
     * @param index The index of the task to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a {@code Task} from the task list by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task with the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return A {@code List} of {@code Task} objects in the task list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints out the task list along with its status (done or not done).
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

}
