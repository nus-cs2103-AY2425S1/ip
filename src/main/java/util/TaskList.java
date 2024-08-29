package util;

import java.util.List;
import tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addTask(Task task, Storage s) {
        this.tasks.add(task);
        s.addToStorage(task.toString());
    }

    /**
     * Method to delete a task from the list.
     * 
     * @param idx The idx of the task to be deleted starting from 1.
     */
    public Task deleteTask(int idx, Storage storage) {
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
        Task entry = this.tasks.get(--idx);
        if (entry.isDone()) {
            entry.markUndone();
            storage.updateStorage(entry.toString(), idx);
        } else {
            return null;
        }
        return entry;
    }

    public int size() {
        return this.tasks.size();
    }

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
