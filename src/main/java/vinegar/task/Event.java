package vinegar.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Vinegar application.
 * <p>
 * An Event task contains a description, a start time, and an end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event in "yyyy-MM-dd HH:mm" format.
     * @param to The end time of the event in "yyyy-MM-dd HH:mm" format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time as a LocalDateTime object.
     * @param to The end time as a LocalDateTime object.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string in the format "[E][ ] description (from: MMM d yyyy HH:mm to: MMM d yyyy HH:mm)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(outputFormatter) + " to: " + to.format(outputFormatter) + ")";
    }

    /**
     * Converts the event task to a file-friendly format.
     *
     * @return A string in the format "E | isDone | description | from | to".
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(inputFormatter) + " | " + to.format(inputFormatter);
    }
}
