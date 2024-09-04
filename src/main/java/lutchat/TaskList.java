package lutchat;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks.
 * It provides methods to add, delete, mark as done, and unmark tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with a pre-existing list of tasks.
     *
     * @param tasks The list of tasks to be managed by this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the list of tasks managed by this TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the provided index is valid within the current task list.
     *
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Adds a task to the task list and notifies the user through the UI.
     *
     * @param task The task to be added.
     * @param ui   The UI to notify the user.
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Deletes a task from the task list at the specified index and notifies the user through the UI.
     * If the index is invalid, an error message is shown.
     *
     * @param index The index of the task to be deleted.
     * @param ui    The UI to notify the user.
     */
    public void deleteTask(int index, Ui ui) {
        if (!isValidIndex(index)) {
            ui.showError("Invalid task index. Please try again.");
            return;
        }
        Task task = tasks.remove(index);
        ui.showTaskRemoved(task, tasks.size());
    }

    /**
     * Marks the task at the specified index as done and notifies the user through the UI.
     * If the index is invalid, an error message is shown.
     *
     * @param index The index of the task to be marked as done.
     * @param ui    The UI to notify the user.
     */
    public void markTaskAsDone(int index, Ui ui) {
        if (!isValidIndex(index)) {
            ui.showError("Invalid task index. Please try again.");
            return;
        }
        Task task = tasks.get(index);
        task.markAsDone();
        ui.showTaskMarked(task);
    }

    /**
     * Marks the task at the specified index as not done and notifies the user through the UI.
     * If the index is invalid, an error message is shown.
     *
     * @param index The index of the task to be marked as not done.
     * @param ui    The UI to notify the user.
     */
    public void markTaskAsUndone(int index, Ui ui) {
        if (!isValidIndex(index)) {
            ui.showError("Invalid task index. Please try again.");
            return;
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        ui.showTaskUnmarked(task);
    }
}
