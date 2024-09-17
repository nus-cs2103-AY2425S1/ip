package repsmax.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A <code>Deadline</code> object corresponds to a task with a specific due date/time.
 */
public class Deadline extends Task {
    private LocalDateTime by;


    public Deadline(String description, LocalDateTime by, int priority) {
        super(description, priority);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}


