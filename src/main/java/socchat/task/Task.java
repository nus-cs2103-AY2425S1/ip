package socchat.task;

/**
 * The abstract Task class represents a task with a description and a completion status.
 * It provides methods to mark and unmark tasks as done, and to get the task's status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description. The task is initially not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone      the initial completion status of the task
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "[X]" if the task is done, "[]" if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]"); // mark done task with X
    }

    /**
     * Marks the task as done, updating its status and printing a confirmation message.
     */
    public String mark() {
        String respond = "";
        this.isDone = true;
        respond += ("Marked " + "\"" + description + "\"" + " as done\n");
        respond += this.toString() + "\n";
        return respond;
    }

    /**
     * Unmarks the task as not done, updating its status and printing a confirmation message.
     */
    public String unmark() {
        String respond = "";
        this.isDone = false;
        respond += ("Marked " + "\"" + description + "\"" + " as not done\n");
        respond += this.toString() + "\n";
        return respond;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the done status of the task as a string.
     *
     * @return "Done" if the task is done, "Not done" otherwise
     */
    public String getDoneStatus() {
        if (isDone) {
            return "Done";
        } else {
            return "Not done";
        }
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Abstract method to return a string representing the task's data in a format suitable for saving.
     *
     * @return a string to save the task's data
     */
    public abstract String toSave();
}
