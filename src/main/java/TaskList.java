import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private List<Task> tasks = null;

    /**
     * The storage for saving and loading tasks.
     */
    private Storage storage = new Storage();

    /**
     * The constructor.
     *
     * @param storage The storage for saving and loading tasks.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        try {
            tasks = storage.load();
        } catch (BocchiException e) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Saves the tasks to the file.
     */
    public void saveTasks() {
        try {
            storage.save(tasks);
        } catch (BocchiException e) {

        }
    }
}
