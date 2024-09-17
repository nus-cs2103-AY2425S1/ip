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
     * @throws DrBrownException If the index is out of bounds or the list is empty.
     */
    public String removeItem(int index, Ui ui) throws DrBrownException {
        try {
            assert index >= 0 && index < tasks.size() : "Index should be within bounds of tasklist";
            assert ui != null : "Ui should not be null";
            Task deleteTask = this.tasks.get(index);
            tasks.remove(deleteTask);
            return ui.showDeleteTask(this, deleteTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException(Ui.getExceptionWrongIndex());
        }
    }

    /**
     * Finds and displays tasks that match the given keyword.
     * If no matching tasks are found, it displays a message indicating no matches.
     *
     * @param keyword The keyword to search for in the tasks' descriptions.
     * @param ui      The Ui object to display messages to the user.
     * @return A string containing the list of matching tasks or a message indicating no matches.
     * @throws DrBrownException If the task list is empty.
     */
    public String findMatching(String keyword, Ui ui) throws DrBrownException {
        assert keyword != null : "Keyword should not be null";
        assert ui != null : "Ui should not be null";
        if (this.getCount() == 0) {
            throw new DrBrownException(Ui.getExceptionEmptyList());
        }
        StringBuilder result = new StringBuilder().append(ui.showFind()).append("\n");
        int listCount = 1;
        for (Task item : this.tasks) {
            if (item.getDescription().contains(keyword)) {
                result.append(listCount).append(". ").append(item).append("\n");
                listCount++;
            }
        }
        return listCount == 1 ? "Oops! Seems like there are no matching tasks." : result.toString();
    }

    /**
     * Displays all tasks in the TaskList.
     * If the list is empty, it informs the user.
     *
     * @param ui The Ui object to display messages to the user.
     * @return A string containing all tasks in the TaskList.
     * @throws DrBrownException If the task list is empty.
     */
    public String listOut(Ui ui) throws DrBrownException {
        assert ui != null : "Ui should not be null";
        if (this.getCount() == 0) {
            throw new DrBrownException(Ui.getExceptionEmptyList());
        }
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
     * @throws DrBrownException If the index is out of bounds or the list is empty.
     */
    public String markTask(int itemIndex, Ui ui) throws DrBrownException {
        assert itemIndex >= 0 && itemIndex < tasks.size() : "Index should be within bounds of tasklist";
        assert ui != null : "Ui should not be null";
        try {
            Task markTask = this.tasks.get(itemIndex);
            markTask.setStatus(true);
            return ui.showMarkTask(markTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException(Ui.getExceptionWrongIndex());
        }
    }

    /**
     * Unmarks a task as incomplete based on its index.
     * Displays a message confirming the task is unmarked.
     *
     * @param itemIndex The index of the task to be unmarked as incomplete.
     * @param ui        The Ui object to display messages to the user.
     * @return A string message confirming the task has been unmarked as incomplete.
     * @throws DrBrownException If the index is out of bounds or the list is empty.
     */
    public String unmarkTask(int itemIndex, Ui ui) throws DrBrownException {
        assert itemIndex >= 0 && itemIndex < tasks.size() : "Index should be within bounds of tasklist";
        assert ui != null : "Ui should not be null";
        try {
            Task unmarkTask = this.tasks.get(itemIndex);
            unmarkTask.setStatus(false);
            return ui.showUnmarkTask(unmarkTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException(Ui.getExceptionWrongIndex());
        }
    }
}
