package waterfall.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks in the Waterfall chatbot application.
 * <code>TaskList</code> stores tasks in a <code>List</code> object
 * and provides various methods for adding, deleting and updating tasks.
 *
 * @author Wai Hong
 */

public class TaskList {

    private final List<Task> tasks;
    private int num;

    /**
     * Constructs an empty TaskList with no task.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
        num = 0;
    }

    /**
     * Constructs a TaskList initialised with the specified list of tasks.
     *
     * @param tasks The list of tasks to be added into task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        num = tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        num++;
    }

    /**
     * Adds a task to the task list at specified index.
     *
     * @param index The index to be added.
     * @param task The task to be added.
     */
    public void add(int index, Task task) {
        tasks.add(index, task);
        num++;
    }

    /**
     * Deletes task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        tasks.remove(index);
        num--;
    }

    /**
     * Sets the status of the task at specified index to status specified.
     *
     * @param index The index of the task.
     * @param done The status to be set.
     */
    public void setDone(int index, boolean done) {
        tasks.get(index).setDone(done);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of task to be retrieved.
     * @return The task at specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks.
     *
     * @return The number of tasks in lists.
     */
    public int getNum() {
        return num;
    }

    /**
     * Check if the given index is within the valid range of task indices.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    public boolean checkIndex(int index) {
        return index >= 0 && index < num;
    }

    /**
     * Prints the details of all tasks in the task list with a specified indentation.
     *
     * @param indentSpace The number of spaces to indent.
     */
    public String printDetail(int indentSpace) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            Task task = tasks.get(i);
            if (task != null) {
                String taskString = " ".repeat(indentSpace)
                        + Integer.toString(i + 1) + "."
                        + task.toString() + "\n";
                sb.append(taskString);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    /**
     * Retrieves task list with the string contained in the title.
     *
     * @param text string required in the title.
     * @return The list of filtered tasks.
     */
    public List<Task> searchTasks(String text) {
        return tasks.stream().filter(task -> task.getTitle().contains(text)).toList();
    }
}
