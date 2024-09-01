package bmo.task;

/**
 * Represents an abstract task class.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task class
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task
     *
     * @return "X" if the task is done, " " if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the string format of the task to be saved in the file
     *
     * @return "[task type] | [isDone] | [description] | [timings if applicable]"
     */
    public abstract String getSavedFormat();

    /**
     * Returns true if the task description contains the keyword
     *
     * @param keyword the keyword to search for
     * @return true if the task description contains the keyword
     */
    public boolean hasMatchingDescription(String keyword) {
        return this.description.contains(keyword);
    }
}
