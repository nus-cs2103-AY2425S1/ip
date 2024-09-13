package yapmeister.task;

import java.util.ArrayList;
import java.util.function.Predicate;

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
     * @param tasks Given ArrayList to hold tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the TaskList.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
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

    public ArrayList<Task> getFilteredArrayList(Predicate<? super Task> predicate) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task : tasks) {
            if (predicate.test(task)) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    /**
     * Returns number of tasks.
     * @return Number of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }
}
