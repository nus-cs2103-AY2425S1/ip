package pixel;

/**
 * Represents an item to complete
 */
public class Task {
    protected String description; // description of task
    protected boolean isDone; // status of task completed

    /**
     * Constructor method for Task
     *
     * @param description name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark Task as done and changing status to completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark Task as undone and changing status to incomplete
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns icon in form of String to mark status as done or undone
     * for printing of Task when user gives command of list
     *
     * @return string representation of done status ("X" for done, " " for undone)
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns number in form of String to mark status as done or undone
     * for uploading to file
     *
     * @return number representation of done status ("1" for done, "0 for undone)
     */
    public String getStatusNumber() {
        return isDone ? "1" : "0";
    }

    /**
     * Returns the description of the task
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the Task
     * when user types in the command list
     *
     * @return string representation of Task for printing
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Returns the string representation of the Task
     * that will be saved to the file
     *
     * @return string representation of Task for saving in file
     */
    public String getFileString() {
        return String.format("Ta | %s | %s", getStatusNumber(), getDescription());
    }
}
