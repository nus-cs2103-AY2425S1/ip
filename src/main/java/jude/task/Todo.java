package jude.task;

import java.time.LocalDateTime;

/**
 * Represents the simple Task with no deadline or duration.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toStringDetails() {
        return String.format("[T]%s", super.toStringDetails());
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s", "T", getStatusBinary(), getDescription());
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return LocalDateTime.MAX;
    }
}
