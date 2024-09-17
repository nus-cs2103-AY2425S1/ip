package axel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a start and end time.
 * Inherits from {@link Task}.
 */
public class EventTask extends Task {
    /** The start time of the event. */
    protected LocalDateTime from;

    /** The end time of the event. */
    protected LocalDateTime to;

    /**
     * Constructs an {@code EventTask} with the specified description, start time, and end time.
     *
     * @param description A description of the task
     * @param from A string representing the start time in the format "yyyy-MM-dd HHmm"
     * @param to A string representing the end time in the format "yyyy-MM-dd HHmm"
     */
    public EventTask(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Returns a string representation of the {@code EventTask}.
     * The format is: "[E][status] (from: MMM d yyyy hh:mm a to: MMM d yyyy hh:mm a)".
     * For example, "[E][ ] (from: Sep 3 2024 01:30 PM to: Sep 3 2024 03:00 PM)".
     *
     * @return A formatted string representation of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a").withLocale(Locale.US);
        String fromFormatted = from.format(outputFormatter).replace("am", "AM").replace("pm", "PM");
        String toFormatted = to.format(outputFormatter).replace("am", "AM").replace("pm", "PM");
        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }

    /**
     * Returns the string representation of this task suitable for saving to a file.
     * The format is: "E | [status] | [description] | [start time] | [end time]".
     * For example, "E | 0 | Attend meeting | 2024-09-03 1330 | 2024-09-03 1500".
     *
     * @return A string representation of the task in file format
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + taskName + " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
