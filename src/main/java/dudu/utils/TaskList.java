package dudu.utils;

import java.util.ArrayList;

import dudu.task.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with a list of tasks.
     *
     * @param tasks The current list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the stored list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the stored list of tasks.
     *
     * @param task Task to be added.
     * @return Total number of tasks after the new task is added.
     */
    public int addTask(Task task) {
        tasks.add(task);
        return this.tasks.size();
    }

    /**
     * Marks a task as completed.
     *
     * @param index Index of the task to be marked.
     * @return Task after marking it.
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
     * Marks a task as uncompleted.
     *
     * @param index Index of the task to be unmarked.
     * @return Task after unmarking it.
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
     * Deletes a task.
     *
     * @param index Idex of the task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {
        try {
            Task removedTask = tasks.get(index);
            tasks.remove(index);
            return removedTask;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Filters the list of tasks for tasks with description matching the query.
     *
     * @param queries User queries.
     * @return Tasks that have description that includes the query.
     */
    public ArrayList<Task> findTasks(String ... queries) {
        return new ArrayList<>(this.tasks.stream().filter(task -> task.includes(queries)).toList());
    }
}
