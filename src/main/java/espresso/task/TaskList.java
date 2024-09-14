package espresso.task;
import java.util.ArrayList;
import espresso.command.InvalidCommandException;

/**
 * Represents a list of tasks.
 * It Manages adding, removing, retrieving tasks, and provides other helpful methods
 * that can help make use of the task list.
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
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     * @return The added task.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Removes a task from the TaskList by using provided index.
     *
     * @param index The index of the task to be removed.
     * @throws InvalidCommandException If the index is out of range.
     */
    public void removeTask(int index) throws InvalidCommandException{
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new InvalidCommandException("Invalid task index.");
        }
    }

    /**
     * Retrieves a task from the TaskList by using provided index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws InvalidCommandException If the index is out of range.
     */
    public Task getTask(int index) throws InvalidCommandException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new InvalidCommandException("Invalid task index.");
        }
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a string representation of the TaskList, where each task is preceded
     * by its position in the list.
     *
     * @return A string representation of the TaskList.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); ++i) {
            res += i + 1 + "." + tasks.get(i);
            if (i < tasks.size() - 1) {
                res += "\n";
            }
        }
        return res;
    }
}