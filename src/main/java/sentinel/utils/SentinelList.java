package sentinel.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import sentinel.task.Task;

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
    public boolean isSizeOne() {
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
    public boolean isTaskDone(int index) {
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
        if (index < 0 || index >= size()) {
            return null;
        }
        return super.get(index);
    }

    /**
     * Filters tasks in the list based on a keyword.
     * <p>
     * This method iterates through all tasks in the list and adds those whose descriptions contain
     * the specified keyword to a new `SentinelList`.
     * The filtering is case-sensitive.
     * </p>
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A `SentinelList` containing tasks whose descriptions include the given keyword. If no tasks match,
     *     an empty list is returned.
     * */
    public SentinelList filter(String keyword) {
        return this.stream()
                .filter(t -> t.toString().contains(keyword))
                .collect(Collectors.toCollection(SentinelList::new));
    }

}
