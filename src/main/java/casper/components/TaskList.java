package casper.components;

import java.util.ArrayList;
import java.util.Comparator;

import casper.exceptions.IndexOutOfRangeException;
import casper.exceptions.InvalidIndexException;
import casper.tasks.Task;


/**
 * Represents a list of tasks.
 */
public class TaskList {

    /**
     * Initializes a new empty TaskList.
     */
    private ArrayList<Task> tasks;

    /**
     * Initializes a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns a string representation of the TaskList.
     *
     * @return A string listing all tasks, or "No tasks added." if the list is empty.
     */
    @Override
    public String toString() {
        if (this.tasks.size() == 0) {
            return "No tasks added.";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Marks a task as complete.
     *
     * @param index The index of the task to mark.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void markTask(int index) {
        this.tasks.get(index).mark();
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param index The index of the task to unmark.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void unmarkTask(int index) {
        this.tasks.get(index).unmark();
    }

    /**
     * Removes a task from the list.
     *
     * @param index The index of the task to remove.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void removeTask(int index) {
        int oldSize = tasks.size();
        this.tasks.remove(index);
        assert tasks.size() == oldSize - 1;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        int oldSize = tasks.size();
        this.tasks.add(task);
        assert tasks.size() == oldSize + 1;
    }

    /**
     * Gets the string representation of a task at a specific index.
     *
     * @param index The index of the task.
     * @return A string representing the task.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public String getTaskString(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Gets the string representation for saving to local storage of a task at a specific index.
     *
     * @param index The index of the task.
     * @return A string representing the task in a format suitable for saving.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public String getTaskSaveString(int index) {
        return this.tasks.get(index).toSaveString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks the validity of an index argument.
     *
     * @param argument The index argument as a string.
     * @param command The command that requires index validation.
     * @throws IndexOutOfRangeException if the index is out of range.
     * @throws InvalidIndexException if the argument is not a valid integer.
     */
    public void checkIndexValidity(String argument, String command)
            throws IndexOutOfRangeException, InvalidIndexException {
        if (!argument.matches("-?\\d+")) {
            throw new InvalidIndexException(command);
        }
        if (Integer.parseInt(argument) > this.tasks.size() || Integer.parseInt(argument) < 0) {
            throw new IndexOutOfRangeException();
        }
    }

    /**
     * Sorts the tasks in the list using the provided comparator.
     *
     * @param comparator The comparator to determine the order of tasks.
     */
    public void sortTasks(Comparator<Task> comparator) {
        this.tasks.sort(comparator);
    }

    /**
     * Finds tasks in the task list that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A string containing a list of tasks that include the keyword in their description,
     *         formatted with the task's index in the list. Each task is displayed on a new line.
     *         If no tasks match the keyword, the returned string will be empty.
     */
    public String find(String keyword) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).isKeywordInDescription(keyword)) {
                result.append((i + 1)).append(". ").append(this.tasks.get(i));
                if (i < this.tasks.size() - 1) {
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }

    /**
     * Gets a string representation of all tasks in the list in a format suitable for saving.
     *
     * @return A string where each task is represented in a format suitable for saving,
     *         with each task's representation separated by a newline.
     */
    public String toSaveString() {
        StringBuilder saveString = new StringBuilder();
        for (Task task : this.tasks) {
            saveString.append(task.toSaveString()).append("\n");
        }
        return saveString.toString().trim();
    }
}
