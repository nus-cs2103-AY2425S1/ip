package dudu.utils;

import java.util.ArrayList;

import dudu.task.Task;

/**
 * Represents the list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList instance
     *
     * @param tasks The current list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor for it no list of tasks is passed in
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the current list of tasks
     *
     * @return The stored list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the stored list of tasks
     *
     * @param task The task to be added
     * @return The total number of tasks after the new task is added
     */
    public int addTask(Task task) {
        tasks.add(task);
        return this.tasks.size();
    }

    /**
     * Marks a task as completed
     *
     * @param index The index of the task to be marked
     * @return The task after marking it
     */
    public Task markTask(int index) {
        tasks.get(index).markCompleted();
        return tasks.get(index);
    }

    /**
     * Marks a task as uncompleted
     *
     * @param index The index of the task to be unmarked
     * @return The task after unmarking it
     */
    public Task unmarkTask(int index) {
        tasks.get(index).markUncompleted();
        return tasks.get(index);
    }

    /**
     * Deletes a task
     *
     * @param index The index of the task to be deleted
     * @return The deleted task
     */
    public Task deleteTask(int index) {
        Task removed = tasks.get(index);
        tasks.remove(index);
        return removed;
    }

    /**
     * Filters the list of tasks for tasks with description matching the query
     *
     * @param queries The user queries to be used as a filter
     * @return The tasks that have description that includes the query
     */
    public ArrayList<Task> findTasks(String ... queries) {
        return new ArrayList<>(this.tasks.stream().filter(task -> task.includes(queries)).toList());
    }
}
