package chobo;
import java.util.ArrayList;
/**
 * Represents a list of tasks.
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
     * @param tasks Arraylist of tasks to be represented
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        assert tasks != null : "tasks should be set";
    }

    /**
     * Adds task to list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) throws InputException {
        if (tasks.contains(task)) {
            throw new InputException("duplicate");
        }
        tasks.add(task);
        assert tasks.contains(task) : "tasks should contain task";
    }

    /**
     * Deletes task from the list.
     *
     * @param index Index of the task to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns task at particular index.
     *
     * @param index the index
     * @return the task
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns total number of task.
     *
     * @return Total number of task
     */
    public int getTotalTask() {
        return tasks.size();
    }

    /**
     * Returns arraylist of tasks.
     *
     * @return Arraylist of task
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}