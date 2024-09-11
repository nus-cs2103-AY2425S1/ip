package nether.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nether.NetherException;


/**
 * Represents an event task that includes a description, a start date/time, and an end date/time.
 * The {@code EventTask} class inherits from the {@link Task} class and adds specific start and end timings to the task.
 */
public class EventTask extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructs an {@code EventTask} object with the specified description, start, and end date/times.
     *
     * @param description The description of the event task.
     * @param startTime The start date and time of the event in the format {@code yyyy-MM-dd HHmm}.
     * @param endTime The end date and time of the event in the format {@code yyyy-MM-dd HHmm}.
     * @throws NetherException If the date/time format for the start or end timings is invalid.
     */

    public EventTask(String description, String startTime, String endTime) {
        super(description);
        assert startTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}") : "Date format must be YYYY-MM-DD HHmm";
        assert endTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}") : "Date format must be YYYY-MM-DD HHmm";
        this.startTime = getDateTime(startTime);
        this.endTime = getDateTime(endTime);
    }

    /**
     * Returns a parsed date and time of the event.
     * @param timeStr The input date and time in the format {@code yyyy-MM-dd HHmm}.
     * @return Parsed date and time for the event.
     * @throws NetherException If the input date/time does not follow the accepted format.
     */
    private static LocalDateTime getDateTime(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        // Validate the input date/time and then assign them
        try {
            return LocalDateTime.parse(timeStr.trim(), formatter);
        } catch (DateTimeException e) {
            throw new NetherException("the date/time format for the event timing is invalid. Please use "
                    + "the format: yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns the string representation of the {@code EventTask} in the format desired for saving into a data file.
     * The format is: {@code E|status|description|start|end}, where {@code E} indicates an event task.
     *
     * @return A string in the format {@code E|status|description|start|end}.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E|" + this.getStatusIcon() + "|" + this.getDescription() + "|" + this.startTime.format(formatter)
                + "|" + this.endTime.format(formatter);
    }

    /**
     * Returns the string representation of the {@code EventTask}.
     * The format is: {@code [E][status] description (from: start to: end)}, where {@code [E]} indicates an event task.
     *
     * @return A string representation of the {@code EventTask}.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E]" + super.toString() + "(from: " + this.startTime.format(formatter)
                + " to: " + this.endTime.format(formatter) + ")";
    }
}
