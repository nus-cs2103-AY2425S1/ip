package elysia.tasks;

import java.util.ArrayList;

/**
 * Manages a list of tasks. Provides functionality to add, delete, mark as done, unmark, and
 * display tasks. The list of tasks can also be converted to a format suitable for file storage.
 */
public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the list based on its position in the list.
     *
     * @param index The 1-based index of the task to be deleted.
     * @return The task that was removed.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 1 or index > list.size()).
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return list.remove(index-1);
    }

    /**
     * Marks a task as done based on its position in the list.
     *
     * @param taskNumber The 1-based index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 1 or index > list.size()).
     * @throws NullPointerException If the task at the given index is null.
     */
    public void markTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        list.get(taskNumber-1).updateStatus(true);
    }

    /**
     * Unmarks a task as done based on its position in the list.
     *
     * @param taskNumber The 1-based index of the task to be unmarked.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 1 or index > list.size()).
     * @throws NullPointerException If the task at the given index is null.
     */
    public void unmarkTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        list.get(taskNumber-1).updateStatus(false);
    }

    /**
     * Returns the string representation of a task at a specific position in the list.
     *
     * @param taskNumber The 1-based index of the task to be printed.
     * @return A string representation of the task.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 1 or index > list.size()).
     * @throws NullPointerException If the task at the given index is null.
     */
    public String printTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        return list.get(taskNumber-1).toString();
    }

    /**
     * Returns the number of tasks in the list as a string.
     *
     * @return The number of tasks in the list.
     */
    public String getSizeAsString() {
        return "" + list.size();
    }

    /**
     * Converts the entire task list to a string format suitable for saving to a file.
     * Each task is represented in its file format, with tasks separated by newlines.
     *
     * @return A string representation of the task list in file format.
     */
    public String toFile() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += list.get(i).toFile() + "\n";
        }
        return output;
    }

    /**
     * Returns a string representation of the entire task list.
     * Each task is prefixed by its position in the list (1-based index), followed by its string representation.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += (i+1) + "." + list.get(i).toString() + "\n";
        }
        return output;
    }

    public TaskList searchByKeyword(String keyword) {
        TaskList searchResults = new TaskList();

        for (Task task: list) {
            if (task.containsString(keyword)) {
                searchResults.addTask(task);
            }
        }

        return searchResults;
    }
}
