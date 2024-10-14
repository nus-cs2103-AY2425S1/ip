package applemazer;

import java.util.ArrayList;

import tasks.Task;

/**
 * Class that abstracts a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} object which abstracts an {@code ArrayList} of tasks.
     * @param tasks The task list to store.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a given {@code Task} to the {@code TaskList}.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a given {@code Task} from the {@code TaskList}.
     */
    public void remove(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns a {@code Task} from the {@code TaskList}.
     * <p>
     * The {@code Task} to get is specified by the {@code taskNumber}.
     * @param taskNumber The number of the {@code Task} to get from the {@code TaskList}.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Returns {@code true} if the {@code TaskList} is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the size of the {@code TaskList} stored.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the {@code TaskList} stored.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }
}
