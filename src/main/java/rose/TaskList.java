package rose;

import java.util.ArrayList;

import rose.task.Task;

/**
 * The {@code TaskList} class manages a list of tasks in the application.
 *
 * <p>This class provides methods to add, remove, retrieve, and display tasks.
 * It maintains an internal {@link java.util.ArrayList} to store the tasks.</p>
 *
 * @see rose.task.Task
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
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
     * Returns the list of tasks.
     *
     * @return The {@link java.util.ArrayList} of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Displays all tasks in the task list using the provided {@code Ui} instance.
     *
     * @return The list of tasks that the user currently has.
     * @param ui The {@code Ui} instance used to display tasks.
     */
    public String showTasks(Ui ui) {
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String stringToAdd = (i + 1) + ". " + tasks.get(i).toString() + "\n";
            taskString.append(stringToAdd);
        }
        return String.valueOf(taskString);
    }

    public String findTask(String keyword, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.hasWord(keyword)) {
                matchingTasks.add(task);
            }
        }

        return ui.showFind(matchingTasks);

    }
}
