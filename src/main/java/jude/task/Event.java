package jude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jude.JudeException;

/**
 * Represents a task happening inbetween two timings.
 *
 * Stores the date and time as LocalDateTime.
 */
public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Creates the Event object.
     * @param description of the Task.
     * @param from represents the starting time, which the format is expected to be of the direct user input.
     * @param to represents the ending time, which the format is expected to be of the direct user input.
     * @throws JudeException if the format of the time is not in the expected format.
     */
    public Event(String description, String from, String to) throws JudeException {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    /**
     * Creates the Event object.
     * @param description Description of the Task.
     * @param fromDateTime Starting date and time of event, which the format is expected to be of the save file format.
     * @param toDateTime Ending date and time of Event, which the format is expected to be of the save file format.
     * @param isDone Status of the task isDone.
     * @throws JudeException If the format of the time is not in the expected format.
     */
    public Event(String description, String fromDateTime, String toDateTime, boolean isDone) throws JudeException {
        super(description, isDone);
        this.fromDateTime = LocalDateTime.parse(fromDateTime);
        this.toDateTime = LocalDateTime.parse(toDateTime);
    }

    /**
     * Creates the Event object.
     * @param dateTimeStr The input date and time from user.
     * @throws JudeException If the format of the time is not in the expected format.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) throws JudeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, format);
        } catch (DateTimeParseException de) {
            throw new JudeException("Wrong date or time format provided. "
                    + "Provide: day/month/year time (e.g. 1/1/2000 1800).");
        }
    }

    @Override
    public String toStringDetails() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toStringDetails(),
                this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                this.toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s | %s",
                "E", getStatusBinary(), getDescription(), this.fromDateTime, toDateTime);
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return toDateTime;
    }
}
