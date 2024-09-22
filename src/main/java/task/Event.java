package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents an event task with a specific start and end time.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startStr The start time of the event in string format.
     * @param endStr The end time of the event in string format.
     * @throws DateTimeParseException If the start or end time cannot be parsed.
     */
    public Event(String description, String startStr, String endStr) throws DateTimeParseException {
        super(description);
        super.setType(TaskType.EVENT);
        try {
            this.startTime = LocalDateTime.parse(startStr, Task.TO_SELF_FORMATTER);
            this.endTime = LocalDateTime.parse(endStr, Task.TO_SELF_FORMATTER);
        } catch (DateTimeParseException exception) {
            TaskList.mainTaskList.deleteTask(TaskList.mainTaskList.getNumTasks() - 1);
            throw exception;
        }
    }

    /**
     * Loads an event task from a formatted string array obtained from splicing the save file
     * during Storage's load method.
     *
     * @param properties The string array containing the task properties.
     */
    public static void load(String[] properties) {
        try {
            Event newEvent = new Event(properties[2], properties[3], properties[4]);
            if (Objects.equals(properties[1], "1")) {
                newEvent.markAsDone();
            }
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date for loaded event: " + e.getMessage());
        }
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as LocalDateTime.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns a string representation of the event task formatted for saving to a file.
     * The format includes the task type, completion status, description, start time, and end time, separated by " | ".
     *
     * @return The formatted string for saving the event task to file.
     */
    @Override
    public String saveFileFormat() {
        String status = this.isMarkedAsCompleted() ? "1 | " : "0 | ";
        return "E | " + status + this.getDescription() + " | " + getStartTimeFormatted(ReadBy.BOB) + " | " + getEndTimeFormatted(ReadBy.BOB);
    }

    /**
     * Returns the start time formatted for the target reader (USER or BOB).
     *
     * @param target The intended reader.
     * @return The formatted start time as a string.
     */
    private String getStartTimeFormatted(ReadBy target) {
        if (target == ReadBy.BOB) {
            return this.startTime.format(Task.TO_SELF_FORMATTER);
        } else {
            return this.startTime.format(Task.TO_USER_FORMATTER);
        }
    }

    /**
     * Returns the end time formatted for the target reader (USER or BOB).
     *
     * @param target The intended reader.
     * @return The formatted end time as a string.
     */
    private String getEndTimeFormatted(ReadBy target) {
        if (target == ReadBy.BOB) {
            return this.endTime.format(Task.TO_SELF_FORMATTER);
        } else {
            return this.endTime.format(Task.TO_USER_FORMATTER);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (From: " + getStartTimeFormatted(ReadBy.USER) + " To: " + getEndTimeFormatted(ReadBy.USER) + ")";
    }
}

