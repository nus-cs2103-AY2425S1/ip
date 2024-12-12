package duch;

import java.util.ArrayList;

import duch.task.Task;

/**
 * Stores a list of Tasks
 */
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    /**
     * Add task to the task list.
     * 
     * @param task
     */
    public void addTask(Task task) {
        super.add(task);
    }

    /**
     * Delete task from the task list.
     * 
     * @param idx
     */
    public void deleteTask(int idx) {
        super.remove(idx);
    }

    /**
     * Mark a task as done.
     * 
     * @param idx
     */
    public void markTask(int idx) {
        super.get(idx).setDone(true);
    }

    /**
     * Unmark a task.
     * 
     * @param idx
     */
    public void unmarkTask(int idx) {
        super.get(idx).setDone(false);

    }
}
