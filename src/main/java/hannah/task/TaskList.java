package hannah.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages a list of tasks, providing methods to add, delete, and modify tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return A List of Task objects.
     */
    public List<Task> getTasks() {
        return tasks;
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
     * Deletes a task from the task list by its index.
     *
     * @param taskNumber The index of the task to be deleted (1-based index).
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param taskNumber The index of the task to be retrieved (1-based index).
     * @return The Task object at the specified index.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param task The task to be marked as done.
     */
    public void markTask(Task task) {
        if (!task.isDone()) {
            task.setDone();
        }
    }

    /**
     * Unmarks a task (marks it as not done).
     *
     * @param task The task to be unmarked.
     */
    public void unmarkTask(Task task) {
        if (task.isDone()) {
            task.setDone();
        }
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
