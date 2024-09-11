package stan.tasks;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import stan.exceptions.StanInvalidDateTimeFormatException;

/**
 * Represents an Event task in the task list.
 * An Event task has a start time (from) and an end time (to).
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event.
     * @param from The start time of the event in the format yyyy-MM-dd HHmm.
     * @param to The end time of the event in the format yyyy-MM-dd HHmm.
     * @throws StanInvalidDateTimeFormatException If the date and time format is incorrect.
     */
    public Event(String description, String from, String to) throws StanInvalidDateTimeFormatException {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
        validateDateTimeOrder();
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTime The date and time string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws StanInvalidDateTimeFormatException If the date and time format is incorrect.
     */
    private LocalDateTime parseDateTime(String dateTime) throws StanInvalidDateTimeFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new StanInvalidDateTimeFormatException("The event time must be in the format yyyy-MM-dd HHmm.\n"
                    + "E.g. /from 2021-07-29 1000 /to 2021-07-30 2200");
        }
    }

    /**
     * Validates that the start time (from) is before the end time (to).
     *
     * @throws StanInvalidDateTimeFormatException If the start time is not before the end time.
     */
    private void validateDateTimeOrder() throws StanInvalidDateTimeFormatException {
        if (!from.isBefore(to)) {
            throw new StanInvalidDateTimeFormatException("The 'from' date and time must be "
                    + "before the 'to' date and time.");
        }
    }

    /**
     * Converts the Event task to a storage-friendly string format.
     *
     * @return The string representation of the Event task for storage.
     */
    @Override
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("E | %s | %s | %s | %s", (isDone ? "1" : "0"), this.description,
                this.from.format(formatter), this.to.format(formatter));
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

        if (from.toLocalDate().equals(to.toLocalDate())) {
            return String.format("[E]%s (from: %s %s to: %s)",
                    super.toString(),
                    from.format(dateFormatter),
                    from.format(timeFormatter),
                    to.format(timeFormatter));
        } else {
            return String.format("[E]%s (from: %s to: %s)",
                    super.toString(),
                    from.format(dateFormatter) + ", " + from.format(timeFormatter),
                    to.format(dateFormatter) + ", " + to.format(timeFormatter));
        }
    }
}
