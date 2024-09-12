package kobe.task;

/**
 * Represents a general task in the Duke chatbot application.
 * A task has a name and can be marked as done or not done.
 */
public class Task {
    /** The name or description of the task. */
    public final String name;

    /** Indicates whether the task is done. */
    public boolean isDone;

    /**
     * Constructs a Task with the specified name and sets the task as not done by default.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false; // Default is not done
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation indicating whether the task is done or not.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String showDoneOrNot() {
        return (isDone ? "[X]" : "[ ]"); // Return X or space depending on isDone
    }

    /**
     * Returns the string representation of the task for saving to a file.
     * This method provides a basic file format that differentiates between different types of tasks.
     *
     * @return A formatted string representing the task in a file-friendly format.
     */
    public String toFileFormat() {
        return (this instanceof Todo ? "T" : (this instanceof Deadline ? "D" : "E")) + " | " + (isDone ? "1" : "0") + " | " + name;
    }

    /**
     * Returns the string representation of the task, including its completion status.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return showDoneOrNot() + " " + name;
    }
}
