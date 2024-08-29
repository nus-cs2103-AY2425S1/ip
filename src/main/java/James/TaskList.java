package james;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the specified list of tasks.
     * @param tasks List of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList by its index.
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList by its index.
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task as completed by its index.
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Marks a task as not completed by its index.
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unMark();
    }

    /**
     * Prints all tasks in the TaskList to the console.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).printTask());
        }
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
