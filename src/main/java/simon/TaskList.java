package simon;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, remove, and manage tasks within the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int count;

    /**
     * Creates a TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {

        this.tasks = tasks;
        this.count = this.tasks.size();
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this(new ArrayList<Task>());
        this.count = 0;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null : "task cannot be null";
        this.tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task pop(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks a task as done or not done based on the provided flag.
     *
     * @param mark If {@code true}, the task will be marked as done; otherwise, it will be marked as not done.
     * @param index The index of the task to be marked.
     */
    public void markTask(boolean mark, int index) {
        if (mark) {
            this.tasks.get(index).markAsDone();
        } else {
            this.tasks.get(index).markAsNotDone();
        }
    }

    /**
     * Returns a string representation of the task list, including all tasks.
     *
     * @return A string representing the task list.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int count = 1;
        for (Task task : tasks) {
            output.append(("\t")).append(count).append(". ").append(task.toString()).append("\n");
            count++;
        }
        return output.toString();
    }

    /**
     * Returns the list of tasks as an ArrayList.
     *
     * @return An ArrayList containing all tasks in the task list.
     */
    public ArrayList<Task> toArr() {
        return this.tasks;
    }
}
