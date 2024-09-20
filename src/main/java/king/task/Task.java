package king.task;

import java.time.LocalDateTime;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the task's completion status, "X" if done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task's status and description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Creates a Task from a string representation read from a file.
     *
     * @param line A string representing the task in a file format.
     * @return A Task object if the format is valid, null otherwise.
     */
    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Invalid format
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            if (parts.length == 4) {
                LocalDateTime by = LocalDateTime.parse(parts[3]);
                return new Deadline(description, by, isDone);
            }
            break;
        case "E":
            if (parts.length == 5) {
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                return new Event(description, from, to, isDone);
            }
            break;
        default:
            System.out.println("Unrecognized task type: " + type); // Default case to handle unknown types
        }
        return null; // Invalid format
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return A string representing the task in a file format.
     */
    public abstract String toFileString();

    public abstract LocalDateTime getDueDateTime();
}
