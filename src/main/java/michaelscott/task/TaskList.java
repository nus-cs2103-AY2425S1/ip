package michaelscott.task;

import java.util.ArrayList;
import michaelscott.utils.MichaelScottException;

/**
 * This class represents the list used to store all the tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @throws MichaelScottException If the index is out of range.
     */
    public void removeTask(int index) throws MichaelScottException {
        if (index < 0 || index >= tasks.size()) {
            throw new MichaelScottException("Please provide a task in range");
        }
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws MichaelScottException If the index is out of range.
     */
    public Task getTask(int index) throws MichaelScottException {
        if (index < 0 || index >= tasks.size()) {
            throw new MichaelScottException("Please provide a task in range");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Clears all tasks from the list.
     */
    public void clearList() {
        this.tasks = new ArrayList<Task>();
    }

//    Todo: Can add a function which removes all the marked tasks in the list
}
