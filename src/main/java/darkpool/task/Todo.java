package darkpool.task;

/**
 * Represents a to-do task with a description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone The completion status of the to-do task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + this.description;
    }

    /**
     * Returns a string representation of the Todo task formatted for file storage.
     *
     * @return A string representation of the Todo task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return ("T | " + (isDone ? "1" : "0") + " | " + this.description + "\n");
    }

}