package yapbot.util;

import java.util.ArrayList;
import java.util.Iterator;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Task;

/**
 * Handles management of Tasks.
 */
public class TaskList {
    private ArrayList<Task> storedTasks;

    /**
     * Returns an empty TaskList instance.
     */
    public TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    /**
     * Returns a TaskList instance with tasks initialised from input list.
     *
     * @param tasks List of tasks to initialise the instance with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.storedTasks = tasks;
    }

    /**
     * Adds the task to the TaskList instance.
     *
     * @param task Task to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.storedTasks.add(task);
    }

    /**
     * Returns the string representation of the tasks currently in the TaskList.
     *
     * @throws YapBotException If TaskList is empty.
     */
    public String listTasks() throws YapBotException {
        if (storedTasks.isEmpty()) {
            throw new YapBotException("Error, no Tasks found in database.");
        }

        StringBuilder result = new StringBuilder();
        Iterator<Task> iterateStored = storedTasks.iterator();

        int index = 1;
        while (iterateStored.hasNext()) {
            result.append("  ")
                    .append(index)
                    .append(".")
                    .append(iterateStored.next())
                    .append("\n");

            index++;
        }

        // Deletes the extra newline character at the end of the result string
        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

    /**
     * Returns the String representation of tasks currently in the TaskList to be saved on a file.
     * String representation of tasks is in a format that YapBot can parse.
     *
     */
    public String saveTasks() {

        if (storedTasks.isEmpty()) {
            return "";
        }

        Iterator<Task> iterateStored = storedTasks.iterator();
        StringBuilder result = new StringBuilder();

        while (iterateStored.hasNext()) {
            result.append(iterateStored.next().saveTask())
                    .append(System.lineSeparator());
        }

        return result.toString();
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param index Index of task to be removed.
     * @throws YapBotException If index >= size of storedTasks array or index < 0 (ie there is no task for the index).
     */
    public Task deleteTask(int index) throws YapBotException {
        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        return storedTasks.remove(index);
    }

    /**
     * Changes a Task to completed status.
     *
     * @param index Index of task to be marked.
     * @throws YapBotException If index >= size of storedTasks array or index < 0 (ie there is no task for the index).
     */
    public Task markTask(int index) throws YapBotException {
        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        Task task = storedTasks.get(index);
        task.setDone(true);

        return task;
    }

    /**
     * Changes a Task to incomplete status.
     *
     * @param index Index of task to be marked.
     * @throws YapBotException If index >= size of storedTasks array or index < 0 (ie there is no task for the index).
     */
    public Task unmarkTask(int index) throws YapBotException {
        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        Task task = storedTasks.get(index);
        task.setDone(false);

        return task;
    }

    /**
     * Returns a String of Tasks that match the query.
     *
     * @param query Keyword to check for in Task description.
     * @return String of Tasks that match the query.
     */
    public String getMatchingTasks(String query) {
        Iterator<Task> tasks = this.storedTasks.iterator();

        StringBuilder result = new StringBuilder();
        int index = 1;

        while (tasks.hasNext()) {
            Task task = tasks.next();
            if (task.checkTaskname(query)) {
                result.append("  ")
                        .append(index)
                        .append(".")
                        .append(task)
                        .append("\n");
                index++;
            }
        }

        if (result.length() == 0) {
            return null;
        } else {
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
    }

    /**
     * Returns the number of tasks in the TaskList.
     */
    public int size() {
        return this.storedTasks.size();
    }

}
