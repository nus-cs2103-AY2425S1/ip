package task;

/**
 * Represents a task that can be marked as done
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     * @return
     */
    public String mark() {
        this.isDone = !this.isDone;
        return getString();
    }

    public boolean isDone() {
        return this.isDone;
    }


    /**
     * Gets string representation of the task
     * @return string representation of the task
     */
    public String getString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Gets the string that represents task that can be read by Storage class to read and write
     * from
     * @return string presentation of task in storage format
     */
    public String getStorageFormat() {
        return "";
    }
}
