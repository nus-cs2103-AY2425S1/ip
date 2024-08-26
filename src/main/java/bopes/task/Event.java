package bopes.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        this.start = LocalDateTime.parse(start.toLowerCase(), formatter);
        this.end = LocalDateTime.parse(end.toLowerCase(), formatter);

        if (this.end.isBefore(this.start)) {
            throw new IllegalArgumentException("End time must be after start time.");
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
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm a");
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
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.start.format(fileFormat) + " | " + this.end.format(fileFormat);
    }
}
