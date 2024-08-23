package citadel.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Citadel application.
 * This class provides methods to add, remove, retrieve, and manage tasks within the list.
 */
public class TaskList {

    /** The list of tasks. */
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
    }

    /**
     * Constructs a {@code TaskList} with an initial list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the task at the specified index from the list.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A {@link TaskList} containing tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : taskList) {
            if (task.printTask().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
