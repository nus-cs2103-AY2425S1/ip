package hue.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hue.DateUtils;
import hue.HueException;

/**
 * Represents a deadline task with a specific due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    /**
     * Creates a {@code Deadline} task with the given description and due date.
     *
     * @param description The description of the task.
     * @param by The due date and time of the task in the format "yyyy-MM-dd HH:mm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateUtils.parseDateTime(by);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
    /**
     * Converts the task to a format suitable for saving to a file.
     *
     * @return A string representing the task in file format.
     */
    @Override
    public String toFileFormat() {
        String task = "D";

        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description + " | " + this.by.format(DateUtils.DATE_TIME_FORMATTER);
    }

    /**
     * Reschedules the deadline task to a new date.
     *
     * @param newDate The new deadline date in a valid date-time format.
     * @throws HueException If the new date is in an invalid format.
     */
    @Override
    public void reschedule(String newDate) throws HueException{
        try {
            this.by = DateUtils.parseDateTime(newDate);
        } catch (DateTimeParseException e) {
            throw new HueException("Invalid Date Format!");
        }
    }
}
