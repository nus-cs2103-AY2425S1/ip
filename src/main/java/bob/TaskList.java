package bob;
import java.util.ArrayList;

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
     * @param tasks the list of tasks to be managed by this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws BobException {
        if (index < 0 || index >= tasks.size()) {
            throw new BobException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }


    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index the index of the task to be retrieved.
     * @return the task at the specified index.
     * @throws BobException if the index is out of bounds.
     */
    public Task get(int index) throws BobException {
        if (index < 0 || index >= tasks.size()) {
            throw new BobException("Invalid task number.");
        }
        return tasks.get(index);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }


    /**
     * Returns all tasks in the task list.
     *
     * @return an ArrayList of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}