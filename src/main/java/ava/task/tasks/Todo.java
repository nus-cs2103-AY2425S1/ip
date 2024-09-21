package ava.task.tasks;

import ava.task.Task;

/**
 * Represents a to-do
 */
public class Todo extends Task {

    /**
     * Creates a new to-do with the input title
     *
     * @param title the title.
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * Creates a new to-do with title and completion status
     *
     * @param title the title.
     * @param isDone the completion status.
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Returns the string representation of the to-do
     *
     * @return the string representation of the to-do.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Todo: ");
        sb.append(super.toString());
        return sb.toString();
    }

    /**
     * Serializes the to-do to a string
     * <br>
     * Optimizes the task's string representation for storage
     *
     * @return the serialized string.
     */
    public String serialize() {
        return String.format("T,%s", super.serialize());
    }
}
