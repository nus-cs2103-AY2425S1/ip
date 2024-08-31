package taskList;

import tasks.Task;

import java.util.ArrayList;

/**
 * A wrapper class to hold a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    public int length() {
        return tasks.size();
    }

    /**
     * Retrieve and obtain the desired task index
     * NOTE: It is the caller's responsibility to ensure that an OOB exception is not triggered here - hence no error handling
     * @param idx the zero-indexed task index.
     * @return the task at that index.
     */
    public Task getTaskAt(int idx) {
        return tasks.get(idx);
    }

    /**
     * Delete a selected task.
     * @param idx the zero-indexed tsk index.
     * @return the task that has just been deleted.
     */
    public Task deleteTaskAt(int idx) {
        return tasks.remove(idx);
    }

    public void addTask(Task t) { tasks.add(t); }

    /**
     * Get all tasks as an ArrayList.
     * @return A copy of the task list (not the actual copy to prevent mutation of tasks through this method).
     */
    public ArrayList<Task> getAllTasks() {
        // no choice but to do an unchecked cast here because of type erasure.
        return (ArrayList<Task>) tasks.clone();
    }
}
