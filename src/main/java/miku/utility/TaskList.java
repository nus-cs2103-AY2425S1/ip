package miku.utility;

import java.util.ArrayList;

import miku.task.Task;

/**
 * A class extends the ArrayList and stores a ArrayList to store tasks.
 */
public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task object that is to be added to the task list.
     */
    public void addItem(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Delete a task from the taskList.
     *
     * @param num the index of the task to be removed
     */
    public void deleteItem(int num) {
        tasks.remove(num - 1);
    }

    public void mark(int num) {
        tasks.get(num - 1).markDone();
    }

    public void unmark(int num) {
        tasks.get(num - 1).markUndone();
    }

    public void initAdd(Task element) {
        tasks.add(element);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
