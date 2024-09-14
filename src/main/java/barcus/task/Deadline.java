package barcus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with a date to do by
 */
public class Deadline extends Task {
    private static final DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    //protected String by;
    protected LocalDateTime by;

    /**
     * Constructor without isDone
     * @param description of task
     * @param by date
     * @throws DateTimeParseException if date cannot be parsed
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by, fromFormatter);
    }

    /**
     * Constructor with isDone
     * @param description of task
     * @param isDone status of task
     * @param by date
     * @throws DateTimeParseException if date cannot be parsed
     */
    public Deadline(String description, boolean isDone, String tags, String by) throws DateTimeParseException {
        super(description, isDone, tags);
        this.by = LocalDateTime.parse(by, fromFormatter);
    }

    /**
     * Converts to save file friendly string
     * @return string
     */
    @Override
    public String convertToSavedString() {
        return "D | " + super.convertToSavedString() + " | " + this.by.format(fromFormatter);
    }

    /**
     * Returns string representation
     * @return string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(toFormatter) + ")";
    }
}
