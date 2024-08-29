package bob.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an event task where the 'to' date is the same as the 'from' date.
     *
     * @param description The description of the task.
     * @param from The start date and time.
     * @param to The end time.
     */
    public EventTask(String description, LocalDateTime from, String to) {
        super(description);
        this.from = from;
        this.to = parseToDateTime(to);
    }

    /**
     * Creates an event task where the 'to' date has a different date as the 'from' date.
     *
     * @param description The description of the task.
     * @param from The start date and time.
     * @param to The end date and time.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Parses a date and time in the format 'yyyy-MM-dd HH:mm'.
     *
     * @param dateTimeInput The date and time input as a string.
     * @return The parsed date and time in LocalDateTime.
     */
    public static LocalDateTime parseDateTime(String dateTimeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTimeInput, formatter);
    }

    /**
     * Parses a time in the format 'HH:mm'.
     *
     * @param toInput The time input as a string.
     * @return The parsed date and time in LocalDateTime.
     */
    protected LocalDateTime parseToDateTime(String toInput) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            // Try parsing as full date and time
            return LocalDateTime.parse(toInput, fullFormatter);
        } catch (DateTimeParseException e) {
            // If fails, parse as just time and use 'from' date
            LocalTime time = LocalTime.parse(toInput, timeFormatter);
            return LocalDateTime.of(from.toLocalDate(), time);
        }
    }

    @Override
    public LocalDate getDate() {
        return from.toLocalDate();
    }
    @Override
    public String getFrom() {
        return from.toString();
    }

    @Override
    public String getTo() {
        return to.toString();
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        String fromFormatted = from.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma"));
        String toFormatted = to.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma"));
        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }
}