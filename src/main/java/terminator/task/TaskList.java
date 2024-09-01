package terminator.task;

import java.util.ArrayList;

import terminator.command.TerminatorException;

/**
 * The TaskList object representing the list of tasks for the chatbot application.
 */
public final class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds all the elements from {@code arr} to the current task list.
     * @param arr The source array containing elements to be added.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean addAll(ArrayList<Task> arr) {
        return this.taskList.addAll(arr);
    }

    /**
     * Returns the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Writes the task list data to disk.
     *
     * @param storage The storage object to handle the file write operations.
     * @throws TerminatorException if an error occurred while writing to disk.
     */
    public void writeToDisk(Storage storage) throws TerminatorException {
        storage.writeToDisk(this.taskList);
    }

    /**
     * Returns the remaining tasks.
     *
     * @return The tasks remaining as a string.
     */
    public String getTasksRemaining() {
        String objectivesRemaining = "";
        int n = this.taskList.size();
        if (n == 0) {
            objectivesRemaining = "No mission objectives specified";
        } else if (n == 1) {
            objectivesRemaining = "1 objective remaining";
        } else {
            objectivesRemaining = String.valueOf(n) + " objectives remaining";
        }
        return "\n" + objectivesRemaining;
    }
}
