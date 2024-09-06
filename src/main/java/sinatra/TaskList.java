package sinatra;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Sinatra application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        System.out.println("Sinatra.Task added");
    }

    /**
     * Removes a task from the task list.
     *
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        System.out.println("Sinatra.Task removed");
    }

    /**
     * Extends the task list with another list of tasks.
     *
     * @param tasks the list of tasks to add
     */
    public void extend(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to return
     * @return the task at the specified index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param task the task to remove
     */
    public void remove(Task task) {
        this.tasks.remove(task);
    }
}
