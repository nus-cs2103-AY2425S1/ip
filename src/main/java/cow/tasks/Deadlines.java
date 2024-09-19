package cow.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cow.exceptions.MissingParametersException;

/**
 * Represents a Deadline Task.
 */
public class Deadlines extends Task {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private LocalDateTime by;

    /**
     * Creates a Deadline Task.
     *
     * @param description The Description of the Deadline.
     * @param by          by to state the dateline of the task.
     */
    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;

    }

    /**
     * Creates a Deadline Task.
     *
     * @param isDone      The status of the Deadline.
     * @param description The Description of the Deadline.
     * @param by          by to state the dateline of the task.
     * @throws MissingParametersException if arguments are invalid.
     */
    public Deadlines(String isDone, String description, String by) throws MissingParametersException {
        super(isDone, description);
        this.by = parseDateTime(by);
    }

    /**
     * Parses the date and time of the deadline.
     *
     * @param by The date and time of the deadline.
     * @return The date and time of the deadline.
     * @throws MissingParametersException if the date and time is invalid.
     */
    private LocalDateTime parseDateTime(String by) throws MissingParametersException {
        try {
            return this.by = LocalDateTime.parse(by, FORMAT);
        } catch (DateTimeParseException e) {
            throw new MissingParametersException("deadline", "deadline return book /by 2/12/2019 1800");
        }
    }

    /**
     * Prints the date and time of the deadline.
     *
     * @return The date and time of the deadline.
     */
    private String printDateTime() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Returns the string representation of the Deadline Task.
     *
     * @return The string representation of the Deadline Task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printDateTime() + ")";
    }

    /**
     * Returns the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return this.by.toLocalDate();
    }

    /**
     * Returns the save data of the Deadline Task.
     *
     * @return The save data of the Deadline Task.
     */
    @Override
    public String getSaveData() {
        return "D | " + super.getSaveData() + " | " + this.by.format(FORMAT);
    }
}
