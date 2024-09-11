package kietwoforone.tasks;

import kietwoforone.exceptions.KieTwoForOneException;
import java.io.Serializable;

/**
 * Represents a general task.
 * Implements Serializable so that to allow saving and loading from txt file.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a new Task object.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if isDone is true, marks the task with an "X".
     * Marks the task with " " otherwise.
     *
     * @return Mark or blank space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets isDone to true.
     */
    public void setTrue() {
        this.isDone = true;
    }

    /**
     * Sets isDone to false.
     */
    public void setFalse() {
        this.isDone = false;
    }

    /**
     * Sets isDone to true and returns the string representation of the task.
     *
     * @return String representation of the marked task.
     */
    public String markTask() {
        this.setTrue();
        return this.toString();
    }

    /**
     * Sets isDone to false and returns the string representation of the task.
     *
     * @return
     */
    public String unmarkTask() {
        this.setFalse();
        return this.toString();
    }

    /**
     * Compares the date of the task with the specified string input.
     * Since this is a general task with no date return false.
     *
     * @param date
     * @return False
     * @throws KieTwoForOneException
     */
    public boolean compareDate(String date) throws KieTwoForOneException {
        return false;
    }

    /**
     * Returns the string representation of the task, including its status and the description of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
