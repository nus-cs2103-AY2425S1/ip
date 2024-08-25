package terminator.task;

import terminator.command.TerminatorException;

import java.util.ArrayList;

/**
 * The TaskList object representing the list of tasks for the chatbot application.
 */
public final class TaskList {

    private final ArrayList<Task> _taskList;

    public TaskList() {
        this._taskList = new ArrayList<>();
    }

    /**
     * Adds all the elements from {@code arr} to the current task list.
     * @param arr The source array containing elements to be added.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean addAll(ArrayList<Task> arr) {
        return this._taskList.addAll(arr);
    }

    /**
     * Returns the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this._taskList;
    }

    /**
     * Writes the task list data to disk.
     *
     * @param storage The storage object to handle the file write operations.
     * @throws TerminatorException if an error occurred while writing to disk.
     */
    public void writeToDisk(Storage storage) throws TerminatorException {
        storage.writeToDisk(this._taskList);
    }

    /**
     * Prints the number of tasks in the task list.
     */
    public void printTasksRemaining() {
        String objectivesRemaining = "";
        int n = this._taskList.size();
        if (n == 0) {
            objectivesRemaining = "No mission objectives specified";
        } else if (n == 1) {
            objectivesRemaining = "1 objective remaining";
        } else {
            objectivesRemaining = String.valueOf(n) + " objectives remaining";
        }
        System.out.println("\n" + objectivesRemaining);
    }
}
