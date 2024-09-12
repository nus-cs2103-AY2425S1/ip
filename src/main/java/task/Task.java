package task;

import java.util.Objects;

/**
 * The Task class represents a task with a description, status, and type.
 * It provides methods to manage the task's status and format the task for saving.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a Task object with the specified description and type.
     *
     * @param description The description of the task.
     * @param type The type of the task (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     */

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;

        assert type.equals("T") || type.equals("D") || type.equals("E")
                : "The type of a task should only be T, D or E";
        this.type = type;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns whether the task is done.
     *
     * @return {@code true} if the task is done; {@code false} otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done; a blank space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the task's status to the specified value.
     *
     * @param status true to mark the task as done; false to mark it as not done.
     */
    public void setMarkStatus(boolean status) {
        this.isDone = status;
    }


    /**
     * Converts the task to a format suitable for saving to a file.
     *
     * @param separation The string used to separate different fields in the saved format.
     * @return The task in a saved format.
     */
    public String toSavedFormat(String separation) {
        return type + separation + (isDone ? "1" : "0") + separation + description;
    }

    /**
     * Converts saved data to a Task object.
     *
     * @param dataArr The array of strings representing the saved data.
     */
    public void convertSavedDataToTask(String[] dataArr) {
        this.isDone = dataArr[1].equals("1");
        this.description = dataArr[2];
    }

    /**
     * Returns the string representation of the task, which is its description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }

}
