package mgtow.task;

import mgtow.util.InvalidTaskException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time in the MGTOW application.
 * This class extends the Task class and adds functionality for managing events.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a new Event task.
     *
     * @param desc The description of the event.
     * @param start The start date and time of the event in the format "yyyy-MM-dd HHmm".
     * @param end The end date and time of the event in the format "yyyy-MM-dd HHmm".
     * @throws InvalidTaskException If the date format is invalid or if the end time is before the start time.
     */
    public Event(String desc, String start, String end) throws InvalidTaskException {
        super(desc, "E");
        try {
            this.start = LocalDateTime.parse(start, INPUT_FORMAT);
            this.end = LocalDateTime.parse(end, INPUT_FORMAT);
            if (this.end.isBefore(this.start)) {
                throw new InvalidTaskException("End time cannot be before start time");
            }
        } catch (Exception e) {
            throw new InvalidTaskException("Invalid date format. Use yyyy-MM-dd HHmm");
        }
    }

    /**
     * Gets the start date and time of the event.
     *
     * @return The LocalDateTime object representing the start of the event.
     */
    public LocalDateTime getStartDateTime() {
        return start;
    }

    /**
     * Gets the end date and time of the event.
     *
     * @return The LocalDateTime object representing the end of the event.
     */
    public LocalDateTime getEndDateTime() {
        return end;
    }

    public String getStart() {
        return start.format(INPUT_FORMAT);
    }

    public String getEnd() {
        return end.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start.format(OUTPUT_FORMAT) + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Checks if the event is on the given date.
     *
     * @param date The date to check.
     * @return true if the event starts or ends on the given date, false otherwise.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return (getStartDateTime().toLocalDate().equals(date) || getEndDateTime().toLocalDate().equals(date));
    }
}