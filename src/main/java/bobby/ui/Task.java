package bobby.ui;

/**
 * Represents a generic task with a description and a completion status.
 * Provides methods to mark the task as done or not done,
 * and to get the task's status.
 */
public abstract class Task {

    /**
     * Provides the description of the Task
     */
    protected String description;

    /**
     * Boolean indicating if the task is completed
     */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * Task is initalised to not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" if the task is done, otherwise a space character.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status icon of the task.
     * "1" if the task is done, otherwise a space character.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks the task as done. If the task is already done, a message indicating
     * that it is already completed is displayed.
     */
    public String markAsDone() {
        String response;
        if (!this.isDone) {
            this.isDone = true;
            response = "Nice! I've marked this task as done:\n"
                    + "[" + this.getStatusIcon() + "] " + this.description + "\n";
        } else {
            response = "This tasked has been already been completed!\n";
        }
        return response;
    }

    /**
     * Marks the task as not done. If the task is already not done,
     * a message indicating that it is not marked yet is displayed.
     */
    public String unMark() {
        String response;
        if (this.isDone) {
            this.isDone = false;
            response = "OK, I've marked this task as not done yet:\n"
                    + "[" + this.getStatusIcon() + "] " + this.description + "\n";
        } else {
            response = "This tasked has not been marked yet!!\n";
        }
        return response;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Abstract method to get a string representation of the task for storing in a file.
     * Concrete subclasses should provide their own implementation.
     *
     * @return A string representation of the task for storage purposes.
     */
    public abstract String toStore();
}
