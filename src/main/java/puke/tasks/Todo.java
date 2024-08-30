package puke.tasks;

/**
 * Represents a to-do task with a description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description and completion status.
     *
     * @param description the description of the to-do task
     * @param isDone true if the task is completed, false otherwise
     */
    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) markAsDone();
    }

    /**
     * Returns a string representation of the to-do task, including its type indicator.
     *
     * @return a string representation of the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the to-do task to its file format representation.
     *
     * @return the string representation of the to-do task in file format
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
