package com.nimbus;

import java.util.ArrayList;

/**
 * TaskList is a container for Task
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add a task to current TaskList
     * @param task task to be added
     */
    public void add(Task task) {
        assert tasks != null : "Tasks should be non-null";
        tasks.add(task);
    }

    /**
     * Add all task in tasks to current TaskList
     * @param tasks tasks to be added
     */
    public void add(TaskList tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            this.tasks.add(tasks.get(i));
        }
    }

    /**
     * Remove a task by its index (0-indexed)
     * @param index index of the task
     * @return The task removed
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Mark the task as done
     * @param index index of the task
     */
    public void mark(int index) {
        tasks.get(index).setDone();
    }

    /**
     * Mark the task as not done
     * @param index index of the task
     */
    public void unmark(int index) {
        tasks.get(index).setNotDone();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Return all task that contains the keyword
     * @param keyword keyword to check for
     * @return All task that contains the keyword
     */
    public TaskList findAll(String keyword) {
        TaskList ans = new TaskList();
        for (int i = 0; i < this.size(); ++i) {
            if (this.get(i).contains(keyword)) {
                ans.add(this.get(i));
            }
        }
        return ans;
    }
}
