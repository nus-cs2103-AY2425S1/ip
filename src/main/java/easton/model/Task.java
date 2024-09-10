package easton.model;

import java.util.Arrays;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new task with the specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null : "description should not be null";
        this.description = description;
        isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns an icon representation the completion of the task.
     *
     * @return The icon of the completion of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a csv string representation of the task.
     *
     * @return The csv string representation of the task.
     */
    public String getCsvFormat() {
        return (isDone ? "1" : "0") + "," + description;
    }

    /**
     * Checks if the keywords are in the description.
     *
     * @param keywords Keywords to search.
     * @return If the keyword is in the description.
     */
    public boolean hasKeywords(String ... keywords) {
        return Arrays.stream(keywords).anyMatch((x) -> description.matches(".*\\b" + x + "\\b.*"));
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
