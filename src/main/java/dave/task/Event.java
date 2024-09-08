package dave.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dave.exceptions.InvalidDateTimeFormatException;
import dave.exceptions.InvalidDescriptionException;

/**
 * Represents an event task. An event task contains a description, a start date and time,
 * and an end time. It inherits from the Task class.
 */
public class Event extends Task {
    /** The date the event starts */
    private LocalDate fromDate;

    /** The time the event starts */
    private LocalTime fromTime;

    /** The time the event ends */
    private LocalTime toTime;

    /**
     * Creates an event task with the specified description, start date/time, and end time.
     *
     * @param description The description of the event task, which includes the start and end times.
     * @throws InvalidDescriptionException If the format of the description is invalid.
     * @throws InvalidDateTimeFormatException If the date or time is not in the required format.
     */
    public Event(String description) throws InvalidDescriptionException, InvalidDateTimeFormatException {
        super(description.split("/from")[0].trim());
        String[] arguments = description.split("/from ");

        if (arguments.length < 2 || arguments[1].trim().isEmpty()) {
            throw new InvalidDescriptionException("Huh! Please provide an event "
                    + "in the format: <task> /from <start> /to <end>");
        }

        String[] timeParts = arguments[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new InvalidDescriptionException("Huh! Please provide a valid time range "
                    + "in the format: from <start date> <start time> /to <end time>");
        }

        String[] fromParts = timeParts[0].trim().split(" ");
        if (fromParts.length < 2) {
            throw new InvalidDescriptionException("Huh! Please provide a valid time range "
                    + "in the format: from <start date> <start time> /to <end time>");
        }
        this.fromDate = parseDate(fromParts[0]);
        this.fromTime = parseTime(fromParts[1]);
        this.toTime = parseTime(timeParts[1].trim());
    }

    /**
     * Parses the provided date string into a LocalDate.
     *
     * @param date The date string in yyyy-MM-dd format.
     * @return The parsed LocalDate.
     * @throws InvalidDateTimeFormatException If the date is in an incorrect format.
     */
    private LocalDate parseDate(String date) throws InvalidDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid date format. Please use yyyy-MM-dd");
        }
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date as a {@code LocalDate}.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a {@code LocalTime}.
     */
    public LocalTime getFromTime() {
        return fromTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time as a {@code LocalTime}.
     */
    public LocalTime getToTime() {
        return toTime;
    }


    /**
     * Parses the provided time string into a LocalTime.
     *
     * @param time The time string in HHmm format.
     * @return The parsed LocalTime.
     * @throws InvalidDateTimeFormatException If the time is in an incorrect format.
     */
    private LocalTime parseTime(String time) throws InvalidDateTimeFormatException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid time format. Please use HHmm (e.g., 1800 for 6:00 PM)");
        }
    }

    /**
     * Converts the event into a string format that is suitable for saving to a file.
     *
     * @return The formatted string representing the event.
     */
    @Override
    public String write() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String fromDateString = fromDate != null ? fromDate.format(dateFormatter) : "unknown date";
        String fromTimeString = fromTime != null ? fromTime.format(timeFormatter) : "no specific time";
        String toTimeString = toTime != null ? toTime.format(timeFormatter) : "no specific time";
        return String.format("E | %d | %s | %s %s - %s\n", this.getIsDone() ? 1 : 0,
                this.getDescription(), fromDateString, fromTimeString, toTimeString);
    }

    /**
     * Returns the string representation of the event task for display purposes.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String fromDateString = fromDate != null ? fromDate.format(dateFormatter) : "unknown date";
        String fromTimeString = fromTime != null ? fromTime.format(timeFormatter) : "";
        String toTimeString = toTime != null ? toTime.format(timeFormatter) : "";
        return "[E]" + super.toString() + " (from: " + fromDateString + " " + fromTimeString
                + " to: " + toTimeString + ")";
    }
}
