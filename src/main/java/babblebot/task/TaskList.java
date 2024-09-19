package babblebot.task;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of Task objects.
 * It provides methods to add, remove, and retrieve tasks, as well as access the size of the list.
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
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList by copying another TaskList.
     *
     * @param taskList The TaskList to copy.
     */
    public TaskList(TaskList taskList) {
        this.tasks = taskList.getAllTasks();
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
     * Deletes a task from the TaskList by index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Determines if a task is in the TaskList.
     *
     * @param task The task that should be checked if it is in the Tasklist.
     * @return The presence of the task in the tasklist
     */
    public Boolean contains(Task task) {
        return tasks.contains(task);
    }

    /**
     * Retrieves a task from the TaskList by index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Replaces a task in the TaskList at the specified index.
     *
     * @param index The index of the task to replace.
     * @param task The new task that will replace the existing task.
     */
    public void set(int index, Task task) {
        tasks.set(index, task);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of all tasks in the TaskList.
     *
     * @return An ArrayList of all tasks in the TaskList.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the list of tasks which matches the keyword, in the TaskList.
     *
     * @param keyword The supplied keyword string.
     * @return An ArrayList of keyword-matching tasks in the TaskList
     */
    public ArrayList<Task> getMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
