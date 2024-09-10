package Buu;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the GPT application.
 * This class provides methods to add, delete, mark, and unmark tasks, as well as to retrieve the list of tasks.
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
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws TaskException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public void deleteTask(int index) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("The task number is out of range.");
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as done in the TaskList at the specified index and optionally sets the priority.
     * If no priority is provided, it defaults to "Low priority" (priority level 1).
     *
     * @param index    The index of the task to be marked as done.
     * @throws TaskException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public void markTask(int index) throws TaskException {
        markTask(index, 1); // Default priority level 1 (Low priority)
    }

    /**
     * Marks a task as done in the TaskList at the specified index and sets the priority.
     *
     * @param index    The index of the task to be marked as done.
     * @param priority The priority level to set for the task.
     * @throws TaskException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public void markTask(int index, int priority) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("The task number is out of range.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
    }

    /**
     * Unmarks a task as not done in the TaskList at the specified index.
     *
     * @param index The index of the task to be unmarked as not done.
     * @throws TaskException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public void unmarkTask(int index) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("The task number is out of range.");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    public void setTaskPriority(int index, int priority) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("The task number is out of range.");
        }
        Task task = tasks.get(index);
        task.setPriority(priority); // Set the provided priority
    }


    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
