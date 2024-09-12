import java.util.ArrayList;
import java.util.List;

/**
 * TaskList has multiple operations to perform on list
 */
public class TaskList {
    private final List<Task> arr;

    public TaskList(List<Task> arr) {
        this.arr = arr;
    }

    /**
     * Initialise the TaskList
     */
    public TaskList() {

        this.arr = new ArrayList<Task>();
    }

    /**
     * adds a Task to the array
     * @param task a task
     */
    public void add(Task task) {

        this.arr.add(task);
    }

    /**
     * adds a Task to the array at the specified index
     * @param task
     * @param number
     */
    public void add(Task task, int number) {
        this.arr.add(number - 1, task);
    }

    /**
     * removes the Task given the Task number and returns the Task that is removed
     * @param number the task number
     * @return Task
     */
    public Task delete(int number) {
        return this.arr.remove(number);
    }

    /**
     * displays the Task in the array
     * @return list of tasks
     */
    public List<Task> showList() {
        return this.arr;
    }

    /**
     * Marks the Task given the Task number, and returns the Task
     * @param num the task number
     * @return Task
     */
    public Task mark(int num) {
        Task chosen = this.arr.get(num - 1);
        chosen.markDone();
        return chosen;
    }

    /**
     * Unmark the Task given the Task number, and returns the Task
     * @param num the task number
     * @return Task
     */
    public Task unmark(int num) {
        Task chosen = this.arr.get(num - 1);
        chosen.markUndone();;
        return chosen;
    }
}
