package friday;

import friday.task.Task;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks. It provides methods to add, delete, mark, and unmark tasks,
 * as well as retrieve the list of tasks and check its status.
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
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList by its index.
     *
     * @param taskIndex The index of the task to delete.
     * @return The task that was removed, or null if the index is invalid.
     */
    public Task deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            return removedTask;
        } else {
            return null;
        }
    }

    /**
     * Marks a task as done by its index.
     *
     * @param taskIndex The index of the task to mark as done.
     * @return The task that was marked as done, or null if the index is invalid.
     */
    public Task markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task markedTask = tasks.get(taskIndex);
            markedTask.markAsDone();
            return markedTask;
        } else {
            return null;
        }
    }

    /**
     * Unmarks a task as done by its index.
     *
     * @param taskIndex The index of the task to unmark as done.
     * @return The task that was unmarked as done, or null if the index is invalid.
     */
    public Task unmarkTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task unmarkedTask = tasks.get(taskIndex);
            unmarkedTask.unmarkAsDone();
            return unmarkedTask;
        } else {
            return null;
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isTaskListEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Retrieves a task by its index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }
}