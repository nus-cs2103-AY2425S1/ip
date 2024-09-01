package oyster.tasks;

import java.util.ArrayList;

/**
 * TaskList class that holds Tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Insert a Task into the list.
     *
     * @param task Inserts a Task at the end.
     */
    public void insert(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a Task in the list.
     *
     * @param index Index to remove Task.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Delete a Task in the list and returns it.
     *
     * @param index Index to remove Task.
     * @return Removed Task object.
     */
    public Task pop(int index) {
        return tasks.remove(index);
    }

    /**
     * Mark a Task in the list.
     *
     * @param index Index of Task to mark.
     */
    public void mark(int index) {
        tasks.get(index).mark();
    }

    /**
     * Unmark a Task in the list.
     *
     * @param index Index of Task to unmark.
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Checks if the list is empty.
     *
     * @return True if the TaskList has no Tasks.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a Task at the index.
     *
     * @param index Index of the Task in the TaskList.
     * @return Task object at the index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     *
     * @return Number of Tasks in the TaskList.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * A copy of the ArrayList used to hold all the Tasks.
     *
     * @return A copy of the ArrayList used to hold all the Tasks.
     */
    public ArrayList<Task> getItems() {
        @SuppressWarnings("unchecked")
        ArrayList<Task> copy = (ArrayList<Task>) tasks.clone();
        return copy;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += String.format("%d. %s", i + 1, tasks.get(i)) + (i < tasks.size() - 1 ? "\n" : "");
        }

        return result;
    }
}
