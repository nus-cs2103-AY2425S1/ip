package calebyyy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E]" + super.toString()
                + " (from: " + from.format(outputFormatter)
                + " to: " + to.format(outputFormatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E" + super.toSaveFormat() + " | " + from.format(inputFormatter) + " | " + to.format(inputFormatter);
    }
}
