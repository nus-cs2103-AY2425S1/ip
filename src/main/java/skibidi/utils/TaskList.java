package skibidi.utils;

import java.util.ArrayList;

import skibidi.task.Task;


/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index Index of the task to be deleted.
     * @return Task that was deleted.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task to be returned.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public TaskList findTask(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }
    public void unmarkTaskAsDone(int index) {
        tasks.get(index).markAsNotDone();
    }
    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }
    public void clear() {
        tasks.clear();
    }
}

