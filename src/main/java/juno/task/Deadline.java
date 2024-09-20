package juno.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.google.gson.annotations.Expose;

import juno.manager.exception.TaskManagerException;

/**
 * Represents a deadline task type with a description and end time.
 * Subclass of Task class and adds functionality to handle deadline-specific details.
 */
public class Deadline extends Task {
    private static final String DATE_TIME_EXCEPTION_STRING = "Your format for date is wrong! Please use this format: "
            + "add deadline {TASK_DESCRIPTION}"
            + "/yyyy MM dd hh.mma (e.g. add deadline homework /2024 11 17 10.00AM)";
    private static final String DATE_TIME_FORMAT_STRING = "yyyy MM dd hh.mma";
    protected LocalDateTime endTime;
    @Expose
    protected String endTimeString;

    /**
     * Constructs a Deadline instance which takes in a specified description, end time, and task type.
     * Calls its superclass Task.
     * Parses end time from string to LocalDateTime object.
     * Throws an exception if the date-time format is invalid.
     *
     * @param description The description of the deadline.
     * @param endTimeString The end time as a string in the format "yyyy MM dd hh.mma".
     * @param taskType The type of the task, i.e. "deadline".
     *
     * @throws TaskManagerException If the date-time format is incorrect.
     */
    public Deadline(String description, String endTimeString, String taskType) throws TaskManagerException {
        super(description, taskType);
        try {
            this.endTime = LocalDateTime.parse(endTimeString.trim(), DateTimeFormatter.ofPattern(
                    DATE_TIME_FORMAT_STRING));
        } catch (DateTimeParseException e) {
            throw new TaskManagerException(DATE_TIME_EXCEPTION_STRING,
                    TaskManagerException.ErrorType.INVALID_DATETIME_ARGUMENT);
        }
        this.endTimeString = endTimeString.trim();
    }

    /**
     * Returns a string representation of the deadline.
     * Formats the LocalDateTime object for display.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[⏰ Deadline] " + super.toString() + " - Don't miss it! ⏳ (due: "
                + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mma")) + ")";
    }

    /**
     * Returns the end time string.
     *
     * @return The end time string.
     */
    public String getEndTimeString() {
        return this.endTimeString;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

}

