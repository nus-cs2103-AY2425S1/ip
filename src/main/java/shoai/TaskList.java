package shoai;
import shoai.Task; // Imports the Task class for task management
import shoai.ShoAIException; // Imports the ShoAIException class for handling exceptions
import java.util.ArrayList; // Imports ArrayList for task storage

/**
 * Represents a list of tasks with methods to add, delete, and retrieve tasks.
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
     * Constructs a non-empty TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) throws ShoAIException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShoAIException("Task number out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task removeTask(int index) throws ShoAIException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShoAIException("Task number out of range.");
        }
        return tasks.remove(index);
    }

    /**
     * Gets item at a specific index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
