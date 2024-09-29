package pixy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task which is an Event.
 */
public class Event extends Task {

    /** From time of the task. */
    protected LocalDateTime from;

    /** To time of the task. */
    protected LocalDateTime to;

    /**
     * Creates an Event object which has a specified duration.
     *
     * @param description The description of the task.
     * @param from The from time of the duration of the task.
     * @param to The to time of the duration of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

        try {
            this.from = LocalDateTime.parse(from.trim(), inputFormatter);
            this.to = LocalDateTime.parse(to.trim(), inputFormatter);
            if (this.from.isAfter(this.to)) {
                throw new IllegalArgumentException("Start time cannot be after end time.");
            }
        } catch (DateTimeParseException e) {
            try {
                this.from = LocalDateTime.parse(from.trim(), displayFormatter);
                this.to = LocalDateTime.parse(to.trim(), displayFormatter);
                if (this.from.isAfter(this.to)) {
                    throw new IllegalArgumentException("Start time cannot be after end time.");
                }
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format: " + from + " or " + to +
                        ". Please use 'd/M/yyyy HHmm' or 'MMM d yyyy, h:mm a'.");
            }
        }
    }

    /**
     * Returns the from time of the task.
     *
     * @return A formatted string representation of the from time.
     */
    public String getFrom() {
        return from.format(DateTimeFormatter
                      .ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * Returns the to time of the task.
     *
     * @return A formatted string representation of the to time.
     */
    public String getTo() {
        return to.format(DateTimeFormatter
                    .ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + getFrom() + " to: " + getTo() + ")";
    }
}
