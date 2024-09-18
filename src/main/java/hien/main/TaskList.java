package hien.main;

import java.util.ArrayList;

import hien.exception.HienException;
import hien.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the {@code TaskList}.
     * This method appends the provided {@code Task} object to the list of tasks.
     *
     * @param task The {@code Task} to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index from the task list.
     * This method removes the task from the list at the given index, if it is valid.
     * If the index is out of bounds, an exception is thrown.
     *
     * @param index The index of the task to be deleted, starting from 0.
     * @throws HienException If the provided index is invalid
     *                      (i.e., less than 0 or greater than or equal to the size of the list).
     */
    public void deleteTask(int index) throws HienException {
        if (index < 0 || index >= tasks.size()) {
            throw new HienException("Task index is out of bounds.");
        }
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as either done or not done.
     *
     * This method updates the completion status of the task at the given index.
     * If {@code isDone} is {@code true}, the task is marked as done. Otherwise,
     * it is marked as undone.
     *
     * If the index is out of bounds, an exception is thrown.
     *
     * @param index The index of the task to be updated, starting from 0.
     * @param isDone A boolean indicating whether the task should be marked
     *               as done ({@code true}) or undone ({@code false}).
     * @throws HienException If the provided index is invalid (i.e., less than 0
     *                       or greater than or equal to the size of the task list).
     */
    public void markTask(int index, boolean isDone) throws HienException {
        if (index < 0 || index >= tasks.size()) {
            throw new HienException("Task index is out of bounds.");
        } else {
            Task task = tasks.get(index);
            if (isDone) {
                task.markAsDone();
            } else {
                task.markAsUndone();
            }
        }
    }

    /**
     * Clears all tasks from the task list.
     *
     * This method removes all tasks in the {@code TaskList}, leaving it empty.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * This method returns the {@code Task} located at the given index.
     * If the index is out of bounds, an exception is thrown.
     *
     * @param index The index of the task to retrieve, starting from 0.
     * @return The {@code Task} at the specified index.
     * @throws HienException If the provided index is invalid (i.e., less than 0
     *                       or greater than or equal to the size of the task list).
     */
    public Task getTask(int index) throws HienException {
        if (index < 0 || index >= tasks.size()) {
            throw new HienException("Task index is out of bounds.");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}

