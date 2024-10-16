package michaelscott.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a period task in the MichaelScott.MichaelScott.
 * A period has a from-time and a to-time in addition to a description.
 */
public class Period extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs a Period task with the specified description, start time, and end time.
     *
     * @param description The description of the Period.
     * @param from        The start time of the Period.
     * @param to          The end time of the Period.
     */
    public Period(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the Period.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the Period.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Returns the description of the Period.
     *
     * @return The description as a String.
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Returns the string representation of the Period.
     *
     * @return String representation of the Period as a String object.
     */
    @Override
    public String toString() {
        return "[P]" + (isDone ? "[X] " : "[ ] ")
                + desc + " (from: " + this.from.format(FORMATTER)
                + " to: " + this.to.format(FORMATTER) + ")";
    }

    /**
     * Returns the In-file representation of the event.
     *
     * @return String in-file representation of the event as a String object.
     */
    @Override
    public String toFile() {
        return "P | " + (isDone ? "1" : "0") + " | "
                + desc + " | " + this.from.format(FORMATTER)
                + " | " + this.to.format(FORMATTER);
    }
}
