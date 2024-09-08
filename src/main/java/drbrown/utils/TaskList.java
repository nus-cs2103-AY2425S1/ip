package drbrown.utils;

import java.util.ArrayList;

import drbrown.task.Task;

/**
 * Represents a list of tasks in the DrBrown application.
 * Provides methods to add, remove, mark, unmark, find, and list tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks to be included in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasklist should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param item The task to be added.
     */
    public void add(Task item) {
        assert item != null : "Task should not be null";
        tasks.add(item);
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList containing all the tasks.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Removes a task from the TaskList based on its index.
     * Displays a message confirming the deletion of the task.
     *
     * @param index The index of the task to be removed.
     * @param ui    The Ui object to display messages to the user.
     * @return A string message confirming the deletion of the task.
     */
    public String removeItem(int index, Ui ui) {
        assert index >= 0 && index < tasks.size() : "Index should be within bounds of tasklist";
        assert ui != null : "Ui should not be null";
        Task deleteTask = this.tasks.get(index);
        tasks.remove(deleteTask);
        return ui.showDeleteTask(deleteTask);
    }

    /**
     * Finds and displays tasks that match the given keyword.
     * If no matching tasks are found, it displays a message indicating no matches.
     *
     * @param keyword The keyword to search for in the tasks' descriptions.
     * @param ui      The Ui object to display messages to the user.
     * @return A string containing the list of matching tasks or a message indicating no matches.
     */
    public String findMatching(String keyword, Ui ui) {
        assert keyword != null : "Keyword should not be null";
        assert ui != null : "Ui should not be null";
        StringBuilder result = new StringBuilder();
        boolean isMatching = false;
        int listCount = 1;
        for (Task item : this.tasks) {
            if (item.getDescription().contains(keyword)) {
                if (!isMatching) {
                    result.append(ui.showFind()).append("\n");
                    isMatching = true;
                }
                result.append(listCount).append(". ").append(item).append("\n");
                listCount++;
            }
        }
        if (!isMatching) {
            return "Oops! Seems like there are no matching tasks.";
        } else {
            return result.toString();
        }
    }

    /**
     * Displays all tasks in the TaskList.
     * If the list is empty, it informs the user.
     *
     * @param ui The Ui object to display messages to the user.
     * @return A string containing all tasks in the TaskList.
     */
    public String listOut(Ui ui) {
        assert ui != null : "Ui should not be null";
        StringBuilder result = new StringBuilder();
        int listCount = 1;
        for (Task item : this.tasks) {
            result.append(listCount).append(". ").append(item).append("\n");
            listCount++;
        }
        return ui.showList() + result.toString();
    }

    /**
     * Marks a task as completed based on its index.
     * Displays a message confirming the task is marked.
     *
     * @param itemIndex The index of the task to be marked as completed.
     * @param ui        The Ui object to display messages to the user.
     * @return A string message confirming the task has been marked as completed.
     */
    public String markTask(int itemIndex, Ui ui) {
        assert itemIndex >= 0 && itemIndex < tasks.size() : "Index should be within bounds of tasklist";
        assert ui != null : "Ui should not be null";
        Task markTask = this.tasks.get(itemIndex);
        markTask.setStatus(true);
        return ui.showMarkTask(markTask);
    }

    /**
     * Unmarks a task as incomplete based on its index.
     * Displays a message confirming the task is unmarked.
     *
     * @param itemIndex The index of the task to be unmarked as incomplete.
     * @param ui        The Ui object to display messages to the user.
     * @return A string message confirming the task has been unmarked as incomplete.
     */
    public String unmarkTask(int itemIndex, Ui ui) {
        assert itemIndex >= 0 && itemIndex < tasks.size() : "Index should be within bounds of tasklist";
        assert ui != null : "Ui should not be null";
        Task unmarkTask = this.tasks.get(itemIndex);
        unmarkTask.setStatus(false);
        return ui.showUnmarkTask(unmarkTask);
    }
}
