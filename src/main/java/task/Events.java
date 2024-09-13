package task;

import buddy.Buddy;
import exceptions.BuddyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * An event task has a description, a start time, and an end time.
 */
public class Events extends Task {
    public LocalDateTime start;
    public LocalDateTime end;


    /**
     * Constructs an Events task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }


    /**
     * Returns the task type of this task.
     *
     * @return A string representing the task type, which is "E" for event tasks.
     */
    @Override
    public String getTaskType() {
        return "E";
    }


    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return A formatted string representing the task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format(
                "E | %d | %s | %s | %s",
                isDone ? 1 : 0,
                description,
                localDateTimeString(start),
                localDateTimeString(end)
        );
    }

    @Override
    public String toString() {
        return description + " (from: " + formatDate(start) + ", to: " + formatDate(end) + ")";
    }

    public void updateStartDate(String newStart) throws BuddyException {
        this.start = stringToDate(newStart);
    }

    public void updateEndDate(String newEnd) throws BuddyException {
        this.end = stringToDate(newEnd);
    }

}