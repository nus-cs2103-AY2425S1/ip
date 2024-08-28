package mira;

import java.util.ArrayList;

/**
 * TaskList handles adding and listing of tasks.
 */
public class TaskList {
    /* Array to store tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to be managed by this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task to delete (1-based).
     */
    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Lists all the tasks currently stored in the task list.
     *
     * @return The string representation of the entire task list.
     */
    public String listTasks() {
        StringBuilder tasksList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksList.append((i + 1)).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                tasksList.append("\n"); // Add newline only if it is not the last task
            }
        }
        return tasksList.toString();
    }

    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to mark as done (1-based index).
     */
    public void markTask(int index) {
        this.tasks.get(index - 1).setStatus(true);
    }

    /**
     * Unmarks the specified task, setting it back to not done.
     *
     * @param index The index of the task to unmark (1-based index).
     */
    public void unmarkTask(int index) {
        this.tasks.get(index - 1).setStatus(false);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve (1-based index).
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 1 || index > size()).
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Returns the list of tasks managed by this mira.TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }
}
