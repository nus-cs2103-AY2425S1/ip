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

    /** Formatter for parsing and displaying date and time. */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     * The task type is set to {@link TaskType#EVENT}.
     *
     * @param description The description of the event task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
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
     */
    private void setStartAndEnd(String startStr, String endStr) throws DateTimeParseException{
        this.start = LocalDateTime.parse(startStr, INPUT_FORMAT);
        this.end = LocalDateTime.parse(endStr, INPUT_FORMAT);
    }

    /**
     * Returns the formatted start and end times.
     *
     * @return An array containing formatted start and end times.
     */
    public String[] getFormattedStartAndEnd() {
        return new String[]{
                start != null ? start.format(OUTPUT_FORMAT) : "No start time set",
                end != null ? end.format(OUTPUT_FORMAT) : "No end time set"
        };
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
