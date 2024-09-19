package juno.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.google.gson.annotations.Expose;

import juno.manager.exception.TaskManagerException;

/**
 * Represents an event task type with a description, start time, and end time.
 * Subclass of Task class and adds functionality to handle event-specific details.
 */
public class Event extends Task {
    private static final String DATE_TIME_EXCEPTION_STRING = "Your format for date is wrong! Please use this format: "
            + "add event {description} "
            + "/yyyy MM dd hh.mma /yyyy MM dd hh.mma .";
    private static final String DATE_TIME_FORMAT_STRING = "yyyy MM dd hh.mma";
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    @Expose
    protected String startTimeString;
    @Expose
    protected String endTimeString;

    /**
     * Constructs an Event instance which takes in a specified description, start time, end time, and task type.
     * Calls its superclass Task.
     * Parses the start and end times from strings to LocalDateTime objects.
     * Throws an exception if the date-time format is invalid.
     *
     * @param description The description of the event.
     * @param startTimeString The start time as a string in the format "yyyy MM dd hh.mma".
     * @param endTimeString The end time as a string in the format "yyyy MM dd hh.mma".
     * @param taskType The type of the task, i.e. "event".
     *
     * @throws TaskManagerException If the date-time format is incorrect.
     */
    public Event(
            String description,
            String startTimeString,
            String endTimeString,
            String taskType) throws TaskManagerException {
        super(description, taskType);
        try {
            this.startTime = LocalDateTime.parse(startTimeString.trim(),
                    DateTimeFormatter.ofPattern("yyyy MM dd hh.mma"));
            this.endTime = LocalDateTime.parse(endTimeString.trim(), DateTimeFormatter.ofPattern(
                    DATE_TIME_FORMAT_STRING));
        } catch (DateTimeParseException e) {
            throw new TaskManagerException(DATE_TIME_EXCEPTION_STRING,
                    TaskManagerException.ErrorType.INVALID_DATETIME_ARGUMENT);
        }
        this.startTimeString = startTimeString.trim();
        this.endTimeString = endTimeString.trim();
    }

    /**
     * Returns a string representation of the event.
     * Formats the LocalDateTime object for display.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[📅 Event] " + super.toString() + " - Mark your calendar! 🗓️ "
                + "(from: " + this.startTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mma"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mma")) + ")";
    }

    /**
     * Returns the end time string.
     *
     * @return The end time string.
     */
    public String getEndTimeString() {
        return this.endTimeString;
    }

    /**
     * Returns the start time string.
     *
     * @return The start time string.
     */
    public String getStartTimeString() {
        return this.startTimeString;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

}
