package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initialized with the given description and its status is set to not done by default.
     * This constructor is intended to be used by concrete subclasses of {@code Task}.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon representing the completion status of the task.
     * If the task is done, the method returns "X". If the task is not done, it returns a space (" ").
     *
     * @return a string representing the task's completion status ("X" if done, " " if not done)
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by setting its status to true.
     * After calling this method, the task will be considered completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its status to false.
     * After calling this method, the task will be considered incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the task's status icon and description.
     * @return a formatted string in the format "[statusIcon] description", where
     * {@code statusIcon} is "X" if the task is done or a space (" ") if not,
     * and {@code description} is the task's description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Returns a raw string representation of the task.
     * This method is intended to be implemented by subclasses to provide a specific format
     * that is used for saving or processing the task data.
     *
     * @return a string representing the task in a raw format
     */
    public abstract String toRawString();

    /**
     * Compares this task to the specified object. The result is {@code true} if and only if
     * the argument is not {@code null} and is a {@code Task} object that represents the same
     * task as this object. Two tasks are considered equal if their raw string representations
     * are equal.
     *
     * @param task the object to compare this task against
     * @return {@code true} if the specified object is equal to this task, {@code false} otherwise
     */

    @Override
    public boolean equals(Object task) {
        if (task == this) {
            return true;
        } else if (task instanceof Task) {
            return this.toRawString().equals(((Task) task).toRawString());
        }
        return false;
    }

    /**
     * Checks if the task description contains the specified text.
     * This method returns {@code true} if the description includes the given text,
     * and {@code false} otherwise.
     *
     * @param text the text to search for within the task description
     * @return {@code true} if the description contains the specified text; {@code false} otherwise
     */
    public boolean contains(String text) {
        return this.description.contains(text);
    }
}


