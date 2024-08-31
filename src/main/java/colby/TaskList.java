package colby;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods to handle the items in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list
     * @param task task that will be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from list and returns it
     * @param index number that refers to the task that is go be deleted in the list
     * @return task that was just removed from the list
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task based on the index of the list input
     * @param index index that refers to the task in the list
     * @return task linked to thr index input to the method
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task linked to the index in the list as done
     * @param index index of the task in the list
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task linked to the index i the list as not done
     * @param index index of the task in the list
     */
    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    /**
     * Returns the number of task in the list
     * @return number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the arraylist
     * @return arraylist containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
