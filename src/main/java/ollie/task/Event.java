package ollie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ollie.exception.EmptyDescriptionException;
import ollie.exception.OllieException;

/**
 * Event is a type of Task.
 * It represents a task with a description, start time, and end time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the Event.
     * @param start The start time of the Event.
     * @param end The end time of the Event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        assert description != null : "Oops! Description cannot be null.";
        assert start != null : "Oops! Start time cannot be null.";
        assert end != null : "Oops! End time cannot be null.";
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start time of the Event.
     *
     * @return start of the Event.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Returns the end time of the Event.
     *
     * @return end of the Event.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Validates the description of the Event task.
     *
     * @param command The command entered by the user.
     * @throws OllieException If the description is empty or the start time and end time are not provided.
     */
    public static void validateDescription(String command) throws OllieException {
        if (command.trim().equalsIgnoreCase("event")) {
            throw new EmptyDescriptionException("event");
        }

        if (!command.contains(" /from:") || !command.contains(" /to:")) {
            throw new OllieException("Please enter in the format:\n"
                    + "event <description> /from: <start time> /to: <end time>\n"
                    + "Example: event meeting /from: 2021-09-30 14:00 /to: 2021-09-30 15:00");
        }

        String description = command.substring(5, command.indexOf(" /from:")).trim();
        String startTime = command.substring(command.indexOf(" /from:") + 7, command.indexOf(" /to:")).trim();
        String endTime = command.substring(command.indexOf(" /to:") + 5).trim();

        // Handles Empty Description, eg: "event /from: 2021-09-30 14:00 /to: 2021-09-30 15:00"
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }

        // Handles Missing Start Date, eg: "event meeting /from: /to: 2021-09-30 15:00"
        if (startTime.isEmpty()) {
            throw new OllieException("Please enter a start time for the task! ☺");
        }

        // Handles Missing End Date, eg: "event meeting /from: 2021-09-30 14:00 /to:"
        if (endTime.isEmpty()) {
            throw new OllieException("Please enter an end time for the task! ☺");
        }
    }

    /**
     * Creates an Event from the specified command.
     *
     * @param command The command entered by the user.
     * @return The Event task created from the command.
     * @throws OllieException If the command is in the wrong format or the date is invalid.
     */
    public static Event createTask(String command) throws OllieException {
        String[] parts = command.substring(5).split(" /from:| /to:");
        validateDescription(command);
        DateTimeFormatter inputDate = Task.getInputDate();
        try {
            return new Event(parts[0].trim(), LocalDateTime.parse(parts[1].trim(), inputDate),
                    LocalDateTime.parse(parts[2].trim(), inputDate));
        } catch (Exception e) {
            throw new OllieException("Please enter the date in the format: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Saves the Event as a string for storage.
     *
     * @return The Event as a string.
     */
    @Override
    public String saveAsString() {
        DateTimeFormatter formatDate = Task.getFormatDate();
        return String.format("%s | %s | %s", super.saveAsString(), start.format(formatDate), end.format(formatDate));
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return The string representation of the Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatDate = Task.getFormatDate();
        return super.toString() + " (from: " + start.format(formatDate) + " to: " + end.format(formatDate) + ")";
    }
}
