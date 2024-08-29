package snipe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Event} class represents a task that occurs at a specific time
 * with a defined start and end time. It is a subclass of {@link Task}.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected LocalDateTime start;

    /** The end time of the event. */
    protected LocalDateTime end;

    /** Formatter for parsing date and time from the input format. */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** Formatter for displaying date and time in the output format. */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     * The task type is set to {@link TaskType#EVENT}.
     *
     * @param description The description of the event task.
     * @param start       The start time of the event in the format "yyyy-MM-dd HHmm".
     * @param end         The end time of the event in the format "yyyy-MM-dd HHmm".
     * @throws DateTimeParseException If the provided start or end time strings cannot be parsed.
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        setStartAndEnd(start, end);
    }

    /**
     * Sets the start and end times by parsing the input strings into {@link LocalDateTime} objects.
     *
     * @param startStr The start time string in "yyyy-MM-dd HHmm" format.
     * @param endStr   The end time string in "yyyy-MM-dd HHmm" format.
     * @throws DateTimeParseException If the provided start or end time strings cannot be parsed.
     */
    private void setStartAndEnd(String startStr, String endStr) throws DateTimeParseException{
        this.start = LocalDateTime.parse(startStr, INPUT_FORMAT);
        this.end = LocalDateTime.parse(endStr, INPUT_FORMAT);
    }

    /**
     * Returns the formatted start and end times for display purposes.
     *
     * @return An array containing the formatted start and end times.
     *         If the start or end time is null, a corresponding message is provided.
     */
    public String[] getFormattedStartAndEnd() {
        return new String[]{
                start != null ? start.format(OUTPUT_FORMAT) : "No start time set",
                end != null ? end.format(OUTPUT_FORMAT) : "No end time set"
        };
    }

    /**
     * Returns a string representation of the task formatted for storage in a file.
     *
     * @return A string representation of the task in the format "E | status | description | start | end".
     */
    @Override
    public String parseToFileFormat() {
        String status = getStatus() ? "1" : "0";
        String[] formattedStartAndEnd = new String[]{
                start != null ? start.format(INPUT_FORMAT) : "",
                end != null ? end.format(INPUT_FORMAT) : ""};
        return "E | " + status + " | " + this.description + " | " + formattedStartAndEnd[0] + " | " + formattedStartAndEnd[1];
    }

    /**
     * Returns a string representation of the event task,
     * including its type indicator, description, start time, and end time.
     *
     * @return A string in the format: "[E][status] description (from: start to: end)".
     */
    @Override
    public String toString() {
        String[] formattedTimes = getFormattedStartAndEnd();
        return "[E]" + super.toString() + " (from: " + formattedTimes[0] + " to: " + formattedTimes[1] + ")";
    }
}
