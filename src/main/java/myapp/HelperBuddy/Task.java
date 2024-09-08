package myapp.helperbuddy;

/**
 * Task abstract class which is inherited from subclasses
 */
abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description should not be null or empty.";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is marked as done.
     * @return true if the task is done, false otherwise.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     * This method sets the completion status of the task to true.
     */
    public void markDone() {
        this.isDone = true;
        assert this.isDone : "Task should be marked as done.";
    }

    /**
     * Marks the task as not done.
     * This method sets the completion status of the task to false.
     */
    public void markUndone() {
        this.isDone = false;
        assert !this.isDone : "Task should be marked as not done.";
    }

    /**
     * Returns a string representation of the task.
     * This is an abstract method that must be implemented by concrete subclasses to provide
     * a specific format for displaying the task.
     * @return A string representing the task.
     */
    public abstract String toString();

    /**
     * Converts the task to a format suitable for saving to a file.
     * This is an abstract method that must be implemented by concrete subclasses to provide
     * a specific format for saving the task to a file.
     * @return A string representing the task in a file-compatible format.
     */
    public abstract String toFileFormat();
}