package chatkaki.tasks;

import chatkaki.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event object with the specified description, start and end.
     *
     * @param description The description of the event.
     * @param start The start of the event.
     * @param end The end of the event.
     */
    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Formats the event task to be saved in the file.
     *
     * @return The formatted event task.
     */
    @Override
    public String fileFormat() {
        return "EVENT," + super.fileFormat() + "," + this.start.format(FORMATTER) + "," + this.end.format(FORMATTER);
    }

    /**
     * Formats the event task to be displayed to the user.
     *
     * @return The formatted event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(FORMATTER) + " to: " + end.format(FORMATTER) + ")" ;
    }
}

