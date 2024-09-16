package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private final DateTimeFormatter display_format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");

    private final String EVENT_ICON = "[E]";
    private final String EVENT = "E";
    private final String FROM = " (from: ";
    private final String TO = " to: ";
    private final String CLOSE = ")";

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.toString() + FROM
                + from.format(display_format) + TO + to.format(display_format) + CLOSE;
    }

    /**
     * Returns string representation of the task to be saved in the text file.
     * @return String of Event
     */
    @Override
    public String toFileString() {
        return EVENT + BAR + (isDone ? "1" : "0") + BAR + description + BAR
                + from.format(display_format) + BAR + to.format(display_format);
    }
}
