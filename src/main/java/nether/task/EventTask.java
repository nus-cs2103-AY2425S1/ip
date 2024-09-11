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
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an {@code EventTask} object with the specified description, start, and end date/times.
     *
     * @param description The description of the event task.
     * @param from The start date and time of the event in the format {@code yyyy-MM-dd HHmm}.
     * @param to The end date and time of the event in the format {@code yyyy-MM-dd HHmm}.
     * @throws NetherException If the date/time format for the start or end timings is invalid.
     */

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = null;
        this.to = null;
        assert from.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}") : "Date format must be YYYY-MM-DD HHmm";
        assert to.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}") : "Date format must be YYYY-MM-DD HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        // Validate the input date/time and then assign them
        try {
            this.from = LocalDateTime.parse(from.trim(), formatter);
        } catch (DateTimeException e) {
            throw new NetherException("the date/time format for the event FROM timing is invalid. Please use "
                    + "the format: yyyy-MM-dd HHmm.");
        }

        try {
            this.to = LocalDateTime.parse(to.trim(), formatter);
        } catch (DateTimeException e) {
            throw new NetherException("the date/time format for the event TO timing is invalid. Please use "
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
        return "E|" + this.getStatusIcon() + "|" + this.getDescription() + "|" + this.from.format(formatter)
                + "|" + this.to.format(formatter);
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
        return "[E]" + super.toString() + "(from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter) + ")";
    }
}
