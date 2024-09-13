package cancelgpt.task;

import java.time.format.DateTimeParseException;

import cancelgpt.exception.task.InvalidTaskException;

/**
 * Represents a generic task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises Task with a description, with initial status
     * of not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialises Task with an initial status set to isDone,
     * description, with initial status
     *
     * @param description the description of the task
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the boolean representation of the integer status given,
     * where 1 represents true.
     *
     * @param statusInt the integer status
     * @return the boolean representation of the integer status given
     */
    public static boolean getStatusBoolean(int statusInt) {
        return (statusInt == 1);
    }

    /**
     * Returns the generated task from the savedDataString obtained from storage.
     *
     * @param savedDataString The string form of the data saved in storage
     * @return the Task created from savedDataString
     * @throws InvalidTaskException if savedDataString cannot be parsed properly to create a task
     */
    public static Task getTaskFromSavedDataString(String savedDataString) throws InvalidTaskException {
        String[] savedDataStringArr = savedDataString.split("\\s*\\|\\s*");
        try {
            if (savedDataStringArr[0].equals("T")) {
                return ToDo.getTaskFromSavedDataStringArr(savedDataStringArr);
            }
            if (savedDataStringArr[0].equals("D")) {
                return Deadline.getTaskFromSavedDataStringArr(savedDataStringArr);
            }
            if (savedDataStringArr[0].equals("E")) {
                return Event.getTaskFromSavedDataStringArr(savedDataStringArr);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("Unable to read task from tasks "
                    + "storage due to incorrect format saved");
        }
        return null;
    }

    /**
     * Returns the status icon of the task, indication whether
     * it is marked as done or not.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the integer representation of the status of the task.
     *
     * @return the integer representation of the status of the task
     */
    public int getStatusInt() {
        return (isDone ? 1 : 0);
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task,
     * which minimally contains a status icon indicating its status,
     * and its description.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon()
                + "] " + this.getDescription();
    }

    /**
     * Returns the string representation of task's data to be saved
     * persistently.
     *
     * @return the string representation of task's data to be saved
     */
    public String getSavedDataString() {
        return this.getStatusInt() + " | " + this.getDescription();
    }
}
