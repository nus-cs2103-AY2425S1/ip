package ipman;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class
 * @author miloaisdino
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor
     * @param description Name of deadline
     * @param stringDate String in DD/MM/YYYY hh[0-23]mm format
     * @throws CommandException Error
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
