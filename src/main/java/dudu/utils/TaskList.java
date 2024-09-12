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
        try {
            tasks.get(index).markCompleted();
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Marks a task as uncompleted
     *
     * @param index The index of the task to be unmarked
     * @return The task after unmarking it
     */
    public Task unmarkTask(int index) {
        try {
            tasks.get(index).markUncompleted();
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Deletes a task
     *
     * @param index The index of the task to be deleted
     * @return The deleted task
     */
    public Task deleteTask(int index) {
        try {
            Task removed = tasks.get(index);
            tasks.remove(index);
            return removed;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Filters the list of tasks for tasks with description matching the query
     *
     * @param queries The user queries to be used as a filter
     * @return The tasks that have description that includes the query
     */
    public ArrayList<Task> findTasks(String ... queries) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            for (String query : queries) {
                if (task.includes(query)) {
                    filteredTasks.add(task);
                    break;
                }
            }
        }
        return filteredTasks;
    }
}
