package task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a task list with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task to be marked as completed.
     */
    public void markTask(int index) {
        tasks.get(index).markAsCompleted();
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param index The index of the task to be marked as uncompleted.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markAsUncompleted();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Sorts the deadlines in the task list chronologically.
     *
     * @return The list of deadlines sorted chronologically.
     */
    public List<Deadline> sortDeadlines() {
        List<Deadline> deadlines = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            }
        }
        deadlines.sort(Comparator.comparing(Deadline::getDeadline));
        return deadlines;
    }
}
