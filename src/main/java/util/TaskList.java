package util;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

/**
 * Utility class to interact with an instance of a list containing user tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for taskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Method to add a new task into the running list.
     *
     * @param task The new task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Method to add a new task into the running list and the storage file.
     *
     * @param task The new task to be added.
     * @param s The storage instance being used.
     */
    public void addTask(Task task, Storage s) {
        assert s != null : "Storage must not be null";
        assert task != null : "Task must not be null";

        this.tasks.add(task);
        s.addToStorage(task.toString());
    }

    /**
     * Method to delete a task from the list.
     *
     * @param idx The idx of the task to be deleted starting from 1.
     */
    public Task deleteTask(int idx, Storage storage) {
        assert idx >= 1 && idx < this.tasks.size() : "Invalid index";
        assert storage != null : "Storage must not be null";

        Task t = this.tasks.remove(--idx);
        storage.removeFromStorage(idx);
        return t;
    }

    /**
     * Mark the selected idx as done.
     *
     * @param idx Idx to be edited. Starting from 1.
     */
    public Task markAsDone(int idx, Storage storage) {
        assert idx >= 1 && idx < this.tasks.size() : "Invalid index";
        assert storage != null : "Storage must not be null";

        Task entry = this.tasks.get(--idx);
        if (!entry.isDone()) {
            entry.markDone();
            storage.updateStorage(entry.toString(), idx);
        } else {
            return null;
        }
        return entry;
    }

    /**
     * Mark the selected idx as undone.
     *
     * @param idx Idx to be edited. Starting from 1.
     */
    public Task markAsUndone(int idx, Storage storage) {
        assert idx >= 1 && idx < this.tasks.size() : "Invalid index";
        assert storage != null : "Storage must not be null";

        Task entry = this.tasks.get(--idx);
        if (entry.isDone()) {
            entry.markUndone();
            storage.updateStorage(entry.toString(), idx);
        } else {
            return null;
        }
        return entry;
    }

    /**
     * Utility method to get the size of the current list.
     *
     * @return Size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Utility method to convert to an array.
     *
     * @return The list as a Task array.
     */
    public Task[] toArray() {
        return this.tasks.toArray(new Task[this.tasks.size()]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int itemNo = 1;

        for (Task task : this.tasks) {
            sb.append(String.format("%d. %s\n", itemNo++, task));
        }
        return sb.toString();
    }

    /**
     * Utility method to check if idx is valid.
     *
     * @param idx The idx to be checked.
     * @return true if is within range else false.
     */
    public boolean isValidIdx(int idx) {
        idx--;
        return idx < this.tasks.size() && idx >= 0;
    }
}
