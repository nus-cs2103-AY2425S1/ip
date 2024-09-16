package com.nimbus;

import java.util.ArrayList;

/**
 * TaskList is a container for Task.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add a task to current TaskList.
     * @param task Task to be added.
     */
    public boolean add(Task task) {
        assert tasks != null : "Tasks should be non-null";
        if (findAllWith(task.getDescription()).size() > 0) {
            return false;
        }
        tasks.add(task);
        return true;
    }

    /**
     * Add all task in tasks to current TaskList.
     * @param tasks The tasks to be added.
     */
    public boolean add(TaskList tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            if (!this.add(tasks.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove a task by its index (0-indexed).
     * @param index The index of the task.
     * @return The task removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Mark the task as done.
     * @param index The index of the task.
     */
    public void mark(int index) {
        tasks.get(index).setDone();
    }

    /**
     * Mark the task as not done.
     * @param index The index of the task.
     */
    public void unmark(int index) {
        tasks.get(index).setNotDone();
    }

    /**
     * Return the size of TaskList.
     * @return The size of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Get a task by index.
     * @param index The index of the task to get.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Return all task that contains the keyword.
     * @param keyword The keyword to check for.
     * @return All task that contains the keyword.
     */
    public TaskList findAllContains(String keyword) {
        TaskList ans = new TaskList();
        for (int i = 0; i < this.size(); ++i) {
            if (this.get(i).contains(keyword)) {
                ans.add(this.get(i));
            }
        }
        return ans;
    }

    /**
     * Return all task with same description as keyword.
     * @param description The description to search for.
     * @return All task with the same description.
     */
    public TaskList findAllWith(String description) {
        TaskList ans = new TaskList();
        for (int i = 0; i < this.size(); ++i) {
            if (this.get(i).getDescription().equals(description)) {
                ans.add(this.get(i));
            }
        }
        return ans;
    }
}
