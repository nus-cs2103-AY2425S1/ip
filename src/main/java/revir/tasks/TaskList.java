package revir.tasks;

import java.io.IOException;
import java.util.ArrayList;

import revir.system.Storage;
import revir.system.exceptions.DuplicateTaskException;

/**
 * Represents a list of tasks.
 * This class manages the tasks and provides methods to add, remove, list, and
 * mark tasks as completed.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList object.
     *
     * @param storage the storage object used to load tasks from a file
     * @param ui      the user interface object used to display error messages
     */
    public  TaskList(Storage storage) {
        this.tasks = new ArrayList<Task>();
        this.storage = storage;
        try {
            this.tasks = storage.loadFromFile();
        } catch (Exception e) {
            tasks = new ArrayList<Task>();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     * @throws IOException if an I/O error occurs while saving the task list to a
     *                     file
     */
    public void add(Task task) throws IOException, DuplicateTaskException {
        for (Task t : tasks) {
            if (t.equals(task)) {
                throw new DuplicateTaskException(task);
            }
        }
        tasks.add(task);
        storage.saveToFile(this.tasks);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index the index of the task to be removed
     * @return a string indicating the task that was deleted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 1 ||
     *                                   index > tasks.size())
     * @throws IOException               if an I/O error occurs while saving the
     *                                   task list to a file
     */
    public String remove(int index) throws IndexOutOfBoundsException, IOException {
        if (tasks.size() == 0) {
            throw new IndexOutOfBoundsException("No tasks to delete.");
        }
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index. Expected index between 1 and " + tasks.size());

        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.saveToFile(this.tasks);
        return "Task deleted: " + task.toString();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return A string containing the string representation of each task in the
     *         list, separated by a newline character.
     */
    public String list() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append(tasks.get(i).toString() + '\n');
        }
        if (!str.isEmpty()) {
            str.deleteCharAt(str.length() - 1);
        }
        return str.toString();
    }

    /**
     * Finds and returns a string representation of all tasks in the task list that
     * contain the specified keyword.
     *
     * @param keyword the keyword to search for in the task list
     * @return a string representation of all tasks that contain the specified
     *         keyword, separated by newline characters
     */
    public String find(String keyword) {
        StringBuilder str = new StringBuilder();
        tasks.forEach(task -> {
            if (task.toString().contains(keyword)) {
                str.append(task.toString() + '\n');
            }
        });
        if (!str.isEmpty()) {
            str.deleteCharAt(str.length() - 1);
        }
        return str.toString();
    }

    /**
     * Sets the completion status of a task at the specified index.
     *
     * @param index  the index of the task to set the completion status for
     * @param status the completion status to set for the task
     * @return a string indicating the updated completion status of the task
     * @throws IndexOutOfBoundsException if the index is out of range (index < 1 ||
     *                                   index > tasks.size())
     * @throws IOException               if an I/O error occurs while saving the
     *                                   tasks to a file
     */
    public String setCompleted(int index, boolean status) throws IndexOutOfBoundsException, IOException {
        if (tasks.size() == 0) {
            throw new IndexOutOfBoundsException("No tasks to delete.");
        }
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index. Expected index between 1 and " + tasks.size());

        }

        Task task = tasks.get(index);
        task.setCompleted(status);
        storage.saveToFile(this.tasks);
        return "Task marked as " + (status ? "completed" : "incomplete") + ": " + task.toString();
    }

}
