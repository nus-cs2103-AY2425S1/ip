package fence.tasklist;

import fence.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> items = new ArrayList<>();

    /**
     * Constructs a task list with the tasks stored in an arraylist.
     * @param items List of tasks stored in an arraylist.
     */
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * Returns the element at the specified position in the list.
     * @param i Index of task.
     * @return Task at index i.
     */
    public Task get(int i) {
        return items.get(i);
    }

    /**
     * Returns the number of elements in the list.
     * @return Size of the task list.
     */
    public int size() {
        return items.size();
    }

    /**
     * Appends the given task to the end of the list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        items.add(task);
    }

    /**
     * Marks the task at the specified position within the list displayed as complete.
     * @param i Index of the task as given by the list printed by {@link fence.ui.Ui#list(TaskList)}.
     */
    public void mark(int i) {
        Task task = items.get(i-1);
        task.complete();
    }

    /**
     * Marks the task at the specified position within the list displayed as incomplete.
     * @param i Index of the task as given by the list printed by {@link fence.ui.Ui#list(TaskList)}.
     */
    public void unmark(int i) {
        Task task = items.get(i-1);
        task.undo();
    }

    /**
     * Removes the task at the specified position within the list displayed. Shifts any subsequent elements to the left.
     * @param i Index of the task as given by the list printed by {@link fence.ui.Ui#list(TaskList)}.
     */
    public void delete(int i) {
        items.remove(i - 1);
    }
}
