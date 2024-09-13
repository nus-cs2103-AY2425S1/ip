package secondmind;

/**
 * Represents a general task with a description and status.
 */
public class Task {
    protected String description;
    protected boolean isdone;

    /**
     * Constructor for the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null: "description cannot be null";
        this.description = description;
        this.isdone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isdone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isdone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    public String getStorageRepresentation() {
        return "";
    }

    /**
     * Returns the string representation of the task, including its status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.isdone) {
            sb.append("[X] ");
        } else {
            sb.append("[ ] ");
        }
        sb.append(this.description);
        return sb.toString();
    }
}