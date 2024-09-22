package tasklist;

import java.util.ArrayList;
import java.util.Objects;

import tasks.Task;

/**
 * A wrapper class to hold a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes a task list with existing tasks if any.
     * @param tasks an ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    /**
     * Provides the length of the task list.
     * @return the length of the task list.
     */
    public int length() {
        assert tasks != null;
        return tasks.size();
    }

    /**
     * Retrieve and obtain the desired task index
     * NOTE: No error handling -  It is the caller's responsibility to ensure that an OOB exception is not triggered.
     * @param idx the zero-indexed task index.
     * @return the task at that index.
     */
    public Task getTaskAt(int idx) {
        assert idx >= 0 && idx < tasks.size();
        return tasks.get(idx);
    }

    /**
     * Delete a selected task.
     * @param idx the zero-indexed tsk index.
     * @return the task that has just been deleted.
     */
    public Task deleteTaskAt(int idx) {
        assert idx >= 0 && idx < tasks.size();
        return tasks.remove(idx);
    }

    /**
     * Add a provided task to the end of the task list.
     * @param t The task to append.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Checks if the provided task's description exact matches any other existing task.
     * @param t the task to check against.
     * @return a boolean value to indicate if any matches are found.
     */
    public boolean checkIfDuplicate(Task t) {
        return tasks.stream()
                .map(Task::getDescription)
                .anyMatch(description -> Objects.equals(description, t.getDescription()));
    }

    /**
     * Get all tasks as an ArrayList.
     * @return A copy of the task list (not the actual copy to prevent mutation of tasks through this method).
     */
    public ArrayList<Task> getAllTasks() {
        // no choice but to do an unchecked cast here because of type erasure.
        return (ArrayList<Task>) tasks.clone();
    }
}
