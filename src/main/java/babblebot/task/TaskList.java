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
     * Retrieves a task from the TaskList by index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
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
}