package sentinel.utils;

import sentinel.task.Task;
import java.util.ArrayList;

/**
 * SentinelList is a custom list implementation that extends ArrayList to store Task objects.
 * It provides additional methods to manipulate and query the list of tasks.
 */
public class SentinelList extends ArrayList<Task> {
    /**
     * Default constructor for SentinelList.
     */
    public SentinelList() {
    }

    /**
     * Checks if the list contains exactly one task.
     *
     * @return true if the list contains one task, false otherwise.
     */
    public boolean sizeOne() {
        return size() == 1;
    }

    /**
     * Retrieves the listed string representation of the task at the specified index.
     *
     * @param index the index of the task.
     * @return the listed string representation of the task.
     */
    public String getListedString(int index) {
        return get(index).listedString();
    }

    /**
     * Toggles the completion status of the task at the specified index.
     *
     * @param index the index of the task.
     */
    public void toggleMark(int index) {
        if (get(index).isDone()) {
            get(index).setUndone();
        } else {
            get(index).setDone();
        }
    }

    /**
     * Toggles the completion status of the specified task.
     *
     * @param t the task to toggle the completion status.
     */
    public void toggleMark(Task t) {
        if (t.isDone()) {
            t.setUndone();
        } else {
            t.setDone();
        }
    }

    /**
     * Checks if the task at the specified index is marked as done.
     *
     * @param index the index of the task.
     * @return true if the task is done, false otherwise.
     */
    public boolean taskIsDone(int index) {
        return get(index).isDone();
    }

    /**
     * Retrieves the task at the specified index. Returns null if the index is out of bounds.
     *
     * @param index the index of the task.
     * @return the task at the specified index, or null if the index is out of bounds.
     */
    @Override
    public Task get(int index) {
        if (index < 0 || index >= size()) return null;
        return super.get(index);
    }
}
