package donna.task;

import donna.DonnaException;

/**
 * Represents a task with a description and a completion status.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     * @throws DonnaException If the description is empty.
     */
    public Task(String description) throws DonnaException {
        assert description != null : "Task description should not be null";

        if (description.trim().isEmpty()) {
            throw DonnaException.emptyDescription(this.getClass().getSimpleName());
        }

        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task's description contains the specified keyword.
     *
     * @param keyword The keyword to search for in the task's description.
     * @return true if the keyword is found in the description, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword);
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the type of task as a string.
     * This method should be implemented by subclasses to return their specific type.
     *
     * @return The 'T' / 'D' / 'E' for Todo, Deadline and Event tasks respectively.
     */
    public abstract String getType();

    /**
     * Converts the task's data into a format suitable for saving to a file.
     * This method should be implemented by subclasses to return their specific file format.
     *
     * @return A string representing the task in a fixed format.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task.
     * Includes the task type, completion status, and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        String isDoneStr = isDone ? "[X] " : "[ ] ";
        return "[" + getType() + "]" + isDoneStr + description + " ";
    }
}
