package GPT;
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
     * @throws GPTException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public void deleteTask(int index) throws GPTException {
        if (index < 0 || index >= tasks.size()) {
            throw new GPTException("The task number is out of range.");
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as done in the TaskList at the specified index.
     *
     * @param index The index of the task to be marked as done.
     * @throws GPTException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public void markTask(int index) throws GPTException {
        if (index < 0 || index >= tasks.size()) {
            throw new GPTException("The task number is out of range.");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task as not done in the TaskList at the specified index.
     *
     * @param index The index of the task to be unmarked as not done.
     * @throws GPTException If the index is out of range (less than 0 or greater than or equal to the list size).
     */
    public void unmarkTask(int index) throws GPTException {
        if (index < 0 || index >= tasks.size()) {
            throw new GPTException("The task number is out of range.");
        }
        tasks.get(index).markAsNotDone();
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
