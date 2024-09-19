package bob.tasklist;

import java.util.ArrayList;
import bob.tasks.Task;
import bob.exception.BobException;

/**
 * Manages the list of tasks in Bob.
 * Provides functionalities to add, remove and search tasks within the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList at the specified index.
     *
     * @param index the index of the task to remove.
     * @return the task that was removed.
     * @throws BobException if the index is out of bounds.
     */
    public Task removeTask(int index) throws BobException {
        if (index < tasks.size() && index >= 0) {
            return tasks.remove(index);
        } else {
            throw new BobException("Invalid index :(");
        }
    }

    /**
     * Finds tasks that match the given keyword.
     * Returns a list of tasks whose string representation contains the keyword, ignoring case.
     *
     * @param keyword the keyword to search for.
     * @return a list of matching tasks.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if(task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
