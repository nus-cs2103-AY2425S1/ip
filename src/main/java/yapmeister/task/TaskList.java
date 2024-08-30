package yapmeister.task;

import java.util.ArrayList;

/**
 * Represents a TaskList that holds tasks
 * @author BlazeChron
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a null TaskList.
     */
    public TaskList() {
        this.tasks = null;
    }

    /**
     * Creates a TaskList with the specified ArrayList.
     * @param tasks Given ArrayList<Task> to hold tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the TaskList.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Deletes task from the TaskList.
     * @param index Index of task to be removed.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.tasks;
    }

    /**
     * Returns number of tasks.
     * @return Number of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns Task at index.
     * @param index Index of the task.
     * @return Task at the index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

}
