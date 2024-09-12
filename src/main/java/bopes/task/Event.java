package bopes.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bopes.exception.BopesException;

/**
 * The Event class represents a task that occurs within a specific time range.
 * It extends the Task class and includes two LocalDateTime fields to store the start and end times.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new Event task with the specified description, start time, end time, and completion status.
     * 
     * @param description the description of the event
     * @param start       the start time of the event in the format "dd/MM/yyyy hh:mm a"
     * @param end         the end time of the event in the format "dd/MM/yyyy hh:mm a"
     * @param isDone      the completion status of the event
     * 
     * @throws IllegalArgumentException if the end time is before the start time
     * @throws BopesException if the date-time format is invalid
     */
    public Event(String description, String start, String end, boolean isDone) throws BopesException {
        super(description, isDone);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty.";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        try {
            // Handle start time
            if (start.trim().length() == 10) { // Length of "dd/MM/yyyy" is 10
                LocalDate startDate = LocalDate.parse(start.trim(), dateFormatter);
                this.start = startDate.atTime(0, 0); // Default to midnight
            } else {
                this.start = LocalDateTime.parse(start.toLowerCase(), dateTimeFormatter);
            }

            // Handle end time
            if (end.trim().length() == 10) { // Length of "dd/MM/yyyy" is 10
                LocalDate endDate = LocalDate.parse(end.trim(), dateFormatter);
                this.end = endDate.atTime(0, 0); // Default to midnight
            } else {
                this.end = LocalDateTime.parse(end.toLowerCase(), dateTimeFormatter);
            }

            assert this.start != null : "Parsed Event start date cannot be null.";
            assert this.end != null : "Parsed Event end date cannot be null.";

            // Check if the end time is before the start time
            if (this.end.isBefore(this.start)) {
                throw new IllegalArgumentException("End time must be after start time.");
            }
        } catch (DateTimeParseException e) {
            throw new BopesException("Error: Invalid date/time format. Please use the format 'dd/MM/yyyy hh:mm a'.");
        }
    }

    /**
     * Returns a string representation of the Event task, including its type, description, 
     * start time, and end time.
     *
     * @return a string representation of the Event task
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "[E]" + super.toString() + " (from: " + this.start.format(outputFormat) + " to: " + this.end.format(outputFormat) + ")";
    }

    /**
     * Returns a string representation of the Event task formatted for file storage.
     * The format includes the task type, completion status, description, start time, and end time.
     *
     * @return a string representation of the Event task in a file-friendly format
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.start.format(fileFormat) + " | " + this.end.format(fileFormat);
    }
}
