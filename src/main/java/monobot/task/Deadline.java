package monobot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    private static final DateTimeFormatter PARSER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private LocalDateTime dueBy;

    /**
     * Constructs a Deadline task with the specified details.
     *
     * @param description The description of the Deadline task.
     * @param dueByString due date/time fo Deadline task.
     */
    public Deadline(String description, String dueByString) throws DateTimeParseException {
        super(description);
        this.dueBy = LocalDateTime.parse(dueByString.trim(), PARSER);
    }

    public LocalDateTime getDueBy() {
        return dueBy;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "[D][X] " : "[D][ ] ";
        return status + this.description.trim() + " (by: " + dueBy.format(FORMATTER) + ")";
    }
}
