package juno.task;

import com.google.gson.annotations.Expose;
import juno.manager.exception.TaskManagerException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task type with a description and end time.
 * Subclass of Task class and adds functionality to handle deadline-specific details.
 */
public class Deadline extends Task {
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
            this.endTime = LocalDateTime.parse(endTimeString.trim(), DateTimeFormatter.ofPattern("yyyy MM dd hh.mma"));
        } catch (DateTimeParseException e) {
            throw new TaskManagerException("Your format for date is wrong! Please use this format: add deadline {description}" +
                    "/yyyy MM dd hh.mma (e.g. add deadline homework /2024 11 17 10.00AM)",
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
        return "[⏰ Deadline] " + super.toString() + " - Don't miss it! ⏳ (due: " +
                this.endTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mma")) + ")";
    }

    /**
     * Returns the end time string.
     *
     * @return The end time string.
     */
    public String getEndTimeString() {
        return this.endTimeString;
    }
}

