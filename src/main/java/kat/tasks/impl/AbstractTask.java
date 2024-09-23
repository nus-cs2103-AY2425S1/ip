package kat.tasks.impl;

import java.time.format.DateTimeFormatter;

import kat.tasks.Task;

/**
 * Provides a base implementation of the Task interface.
 */
public abstract class AbstractTask implements Task {

    protected static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    protected final String description;

    protected boolean isDone;

    /**
     * Constructs a new AbstractTask with the given description.
     *
     * @param description The description of the task.
     */
    protected AbstractTask(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Deserializes a task from a string representation.
     *
     * @param line The string representation of the task.
     * @return The deserialized Task object.
     * @throws IllegalArgumentException if the task format is invalid.
     */
    public static Task deserialize(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format");
        }

        String type = parts[0];

        return switch (type) {
            case "T" -> TodoTask.deserialize(line);
            case "D" -> DeadlineTask.deserialize(line);
            case "E" -> EventTask.deserialize(line);
            default -> throw new IllegalArgumentException("Unknown task type: " + type);
        };
    }

    @Override
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

}
