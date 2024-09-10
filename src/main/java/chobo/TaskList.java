package chobo;
import java.util.ArrayList;
/**
 * The type Task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Instantiates a new Task list.
     *
     * @param tasks the tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        assert tasks != null : "tasks should be set";
    }

    /**
     * Add task.
     *
     * @param task the task
     */
    public void addTask(Task task) throws InputException {
        if (tasks.contains(task)) {
            throw new InputException("duplicate");
        }
        tasks.add(task);
        assert tasks.contains(task) : "tasks should contain task";
    }

    /**
     * Delete task.
     *
     * @param index the index
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets task.
     *
     * @param index the index
     * @return the task
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets total task.
     *
     * @return the total task
     */
    public int getTotalTask() {
        return tasks.size();
    }

    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}