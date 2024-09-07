package ipman;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Extends the Task class to include a due date and time.
 * Provides methods to format and display the deadline.
 *
 * @author miloaisdino
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with a specified description and due date.
     *
     * @param description The name or description of the deadline task.
     * @param stringDate The due date and time in the format "DD/MM/YYYY hh[0-23]mm".
     * @throws CommandException If the date format is incorrect.
     */
    public Deadline(String description, String stringDate) throws CommandException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy kkmm");
            this.by = LocalDateTime.parse(stringDate, formatter);
        } catch (DateTimeParseException e) {
            throw new CommandException("Please format the datetime string as follows: DD/MM/YYYY hh[0-23]mm");
        }
    }

    @Override
    public String toString() {
        String prettyDate = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"));
        return "[D]" + super.toString() + " (by: " + prettyDate + ")";
    }
}
