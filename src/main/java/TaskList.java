import java.util.ArrayList;

/**
 * Contains the list of tasks and operations to modify it
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Deletes the task in the list at an index.
     *
     * @param num the task number to be deleted
     */
    public void delete(int num) {
        this.tasks.remove(num - 1);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the task in the list at an index.
     *
     * @param index the index of the task
     * @return the task at that index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
