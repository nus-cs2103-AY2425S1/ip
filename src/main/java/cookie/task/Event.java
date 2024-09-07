package cookie.task;

import java.time.LocalDate;

import cookie.parser.DateParser;

public class Event extends Task {
    private String from;
    private String to;
    private LocalDate fromDeadline;
    private LocalDate toDeadline;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event, formatted as a string
     * @param to the end time of the event, formatted as a string
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        fromDeadline = DateParser.convertStringToDate(this.from);
        toDeadline = DateParser.convertStringToDate(this.to);
    }

    /**
     * Constructs a new {@code Event} task with the specified completion status, description, start time, and end time.
     *
     * @param isDone a boolean indicating whether the task is completed
     * @param description the description of the event
     * @param from the start time of the event, formatted as a string
     * @param to the end time of the event, formatted as a string
     */
    public Event(Boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
        fromDeadline = DateParser.convertStringToDate(this.from);
        toDeadline = DateParser.convertStringToDate(this.to);
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + (fromDeadline != null ? DateParser.changePattern(fromDeadline) : this.from)
                + " to: " + (toDeadline != null ? DateParser.changePattern(toDeadline) : this.to) + " )";
    }
}
