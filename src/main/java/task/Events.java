package task;

import exceptions.BuddyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 * An {@code Events} task includes a description, a start time, and an end time for the event.
 */
public class Events extends Task {
    public LocalDateTime start;
    public LocalDateTime end;

    /**
     * Constructs an {@code Events} task with the specified description, start time, and end time.
     * Ensures that the end time is not earlier than the start time.
     *
     * @param description The description of the event.
     * @param start       The start time of the event, as a {@code LocalDateTime}.
     * @param end         The end time of the event, as a {@code LocalDateTime}.
     * @throws BuddyException If the end time is earlier than the start time, or if the dates are invalid.
     */
    public Events(String description, LocalDateTime start, LocalDateTime end) throws BuddyException {
        super(description, TaskType.EVENT);
        validateDates(start, end);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the task type of this task.
     * For event tasks, the task type is represented by "E".
     *
     * @return A string "E" indicating this is an event task.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     * The format follows: "E | [isDone] | [description] | [start] | [end]".
     *
     * @return A string representing the task formatted for saving to a file.
     */
    @Override
    public String toFileString() {
        return String.format(
                "E | %d | %s | %s | %s",
                isDone ? 1 : 0,
                getDescription(),
                localDateTimeString(start),
                localDateTimeString(end)
        );
    }

    /**
     * Returns a string representation of the task for display, including the start and end times.
     * The start and end times are formatted as human-readable strings.
     *
     * @return A string representing the task with the description, start time, and end time.
     */
    @Override
    public String toString() {
        return getDescription() + " (from: " + formatDate(start) + ", to: " + formatDate(end) + ")";
    }

    /**
     * Updates the start time of the event.
     * The new start time is provided as a string and will be parsed into a {@code LocalDateTime}.
     * Ensures that the new start time is not later than the current end time.
     *
     * @param newStart The new start time as a string.
     * @throws BuddyException If the new start time cannot be parsed into a valid {@code LocalDateTime} or is later than the current end time.
     */
    public void updateStartDate(String newStart) throws BuddyException {
        LocalDateTime newStartDate = parseDate(newStart);
        validateDates(newStartDate, this.end);
        this.start = newStartDate;
    }

    /**
     * Updates the end time of the event.
     * The new end time is provided as a string and will be parsed into a {@code LocalDateTime}.
     * Ensures that the new end time is not earlier than the current start time.
     *
     * @param newEnd The new end time as a string.
     * @throws BuddyException If the new end time cannot be parsed into a valid {@code LocalDateTime} or is earlier than the current start time.
     */
    public void updateEndDate(String newEnd) throws BuddyException {
        LocalDateTime newEndDate = parseDate(newEnd);
        validateDates(this.start, newEndDate);
        this.end = newEndDate;
    }

    /**
     * Validates that the start date is not after the end date.
     *
     * @param start The start date to check.
     * @param end   The end date to check.
     * @throws BuddyException If the end date is earlier than the start date.
     */
    private void validateDates(LocalDateTime start, LocalDateTime end) throws BuddyException {
        if (end.isBefore(start)) {
            throw new BuddyException("The end date cannot be earlier than the start date.");
        }
    }

    /**
     * Parses a string into a {@code LocalDateTime}, ensuring it is a valid date.
     *
     * @param dateString The string representing the date.
     * @return The parsed {@code LocalDateTime}.
     * @throws BuddyException If the string cannot be parsed into a valid {@code LocalDateTime}.
     */
    private LocalDateTime parseDate(String dateString) throws BuddyException {
        try {
            return stringToDate(dateString);
        } catch (DateTimeParseException e) {
            throw new BuddyException("Invalid date format. Please provide a valid date.");
        }
    }
}