package dave.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks. The TaskList class allows for adding, removing,
 * and accessing tasks in a list.
 */
public class TaskList {

    /** The list of tasks stored in this TaskList */
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Sets the task list with a new list of tasks.
     *
     * @param inputTasks the new list of tasks to be set.
     */
    public void setTaskList(ArrayList<Task> inputTasks) {
        assert inputTasks != null : "Input task list should not be null";
        this.tasks = inputTasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task being added should not be null";
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param index the index of the task to be deleted.
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index the index of the task to be retrieved.
     * @return the task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return the size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves all the tasks in the task list.
     *
     * @return an ArrayList containing all the tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     * Iterates through the list of tasks and checks if the task's string representation
     * contains the given keyword. If a match is found, the task is added to the list of
     * matching tasks.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return An {@code ArrayList} of tasks that contain the keyword in their description.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword should not be null or empty";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
