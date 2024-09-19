package jude.task;

/**
 * Represents a Task with a description and completion status.
 *
 * Task can be in a state of as done or not done to keep track of the task status.
 * Provides a textual representation of the task by getStatusIcon for better visualisation for the user.
 *
 * @author Judy Park
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Takes in the description of the task to initialise Task object with a completion status of undone.
     * This constructor is used for taking in a user input.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Takes in description and whether the task status is complete to initialise the Task object.
     * This constructor is used for taking in a save file input.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public int getStatusBinary() {
        return isDone ? 1 : 0;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return description;
    }

    public String toStringDetails() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String toFileFormat() {
        return String.format("%s | %d | %s", " ", getStatusBinary(), getDescription());
    }
}
