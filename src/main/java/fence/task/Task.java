package fence.task;

/**
 * Represents a general task which contains a description.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a general task with the specified description.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isComplete() {
        return this.isDone;
    }

    /**
     * Sets the isDone field as true.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Sets the isDone field as false.
     */
    public void undo() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of this general task with the completion status and description.
     * @return String representation of this general task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.description;
    }

    /**
     * Returns the string representation within the storage file of this general task with the completion status and
     * description.
     * @return String representation within the storage file of this general task.
     */
    public String toTxt() {
        return "(" + (isDone ? "DONE" : "UNDONE") + ") " + this.description;
    }

    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    public abstract boolean isDue();
}
