package gale.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import gale.parser.Parser;

/**
 * Represents a deadline task that has a due date and time.
 *
 * @author kaikquah
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Constructs a deadline task with the given description and due date and time.
     * @param description the description of the deadline
     * @param by the due date and time of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = Parser.parseDateTime(by);
    }

    /**
     * Returns the deadline task as a String.
     * @return the deadline task as a String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }

    /**
     * Returns the deadline task as a String to be written to a file.
     * @return the deadline task as a String to be written to a file
     */
    @Override
    public String toFileString() {
        int status = super.status() ? 1 : 0;
        String byInFileFormat = by.format(Parser.getFormatters().get(0));
        return String.format("D | %d | %s | %s", status, getDescription(),
                byInFileFormat);
    }
}
