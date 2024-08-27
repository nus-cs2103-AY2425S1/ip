package com.nimbus;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> arr;

    public TaskList() {
        arr = new ArrayList<>();
    }

    /**
     * Add a task to current TaskList
     * @param task task to be added
     */
    public void add(Task task) {
        arr.add(task);
    }

    /**
     * Add all task in tasks to current TaskList
     * @param tasks tasks to be added
     */
    public void add(TaskList tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            arr.add(tasks.get(i));
        }
    }

    /**
     * Remove a task by its index (0-indexed)
     * @param index index of the task
     * @return The task removed
     */
    public Task remove(int index) {
        return arr.remove(index);
    }

    /**
     * Mark the task as done
     * @param index index of the task
     */
    public void mark(int index) {
        arr.get(index).setDone();
    }

    /**
     * Mark the task as not done
     * @param index index of the task
     */
    public void unmark(int index) {
        arr.get(index).setNotDone();
    }

    public int size() {
        return arr.size();
    }

    public Task get(int index) {
        return arr.get(index);
    }
}
