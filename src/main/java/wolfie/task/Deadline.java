package wolfie.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        if (by == null || !isValidDate(by.toLocalDate())) {
            throw new IllegalArgumentException("Invalid date provided.");
        }
        this.by = by; // Set the deadline of the task
    }

    private boolean isValidDate(LocalDate date) {
        try {
            date.toString();
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns the deadline task in the format to be saved in the file.
     *
     * @return Deadline task in the format to be saved in the file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | "
                + by.format(DateTimeFormatter.ISO_DATE_TIME);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deadline deadline)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return Objects.equals(by, deadline.by);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), by);
    }
}
