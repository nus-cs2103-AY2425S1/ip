package agave.logic;

import agave.Task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm");

    /**
     * Constructs a Deadline with the specified description and deadline.
     *
     * @param description The description of the deadline.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    @Override
    public String getCorrectFormat() {
        return String.format("%s | %s", getDescription(), by.format(INPUT_FORMAT));
    }

    @Override
    public String toString() {
        return " [D] " + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    public LocalDateTime getBy() {
        return this.by;
    }
}