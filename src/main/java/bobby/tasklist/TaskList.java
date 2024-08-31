package bobby.tasklist;

import bobby.exceptions.InvalidTaskNumberException;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;
import bobby.ui.Ui;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * The {@code TaskList} class represents a list of tasks in the Bobby application.
 * It provides methods to add, remove, and retrieve tasks, check the list's size and emptiness,
 * and find tasks by date. The list can contain various types of tasks such as
 * {@link Deadline}, {@link Event}, and {@link Todo} objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws InvalidTaskNumberException if the index is out of bounds
     */
    public Task get(int index) throws InvalidTaskNumberException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }

    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index the index of the task to remove
     * @return the removed task
     * @throws InvalidTaskNumberException if the index is out of bounds
     */
    public Task remove(int index) throws InvalidTaskNumberException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that are associated with a specific date.
     * This includes tasks with deadlines on the date and events happening on the date.
     *
     * @param date The date to search for in tasks.
     * @return An ArrayList of tasks that match the date.
     */
    public ArrayList<Task> findTasksByDate(LocalDate date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                matchingTasks.add(task);
            } else if (task instanceof Event && ((Event) task).isOnDate(date)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds tasks containing the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that contain the keyword in their description.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().equals(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
