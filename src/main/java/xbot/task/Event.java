package xbot.task;

import xbot.parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a task with a event in the XBot application.
 * A {@code Event} task has a description, a start time and an end time.
 */
public class Event extends Task {
    protected String from, to;

    /**
     * Constructs a new {@code Event} task with the specified description, start time and end time.
     *
     * @param description The description of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.E);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of event.
     *
     * @return The start time as a String.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of event.
     *
     * @return The end time as a String.
     */
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.changeDateFormat(from) + " to: " + Parser.changeDateFormat(to) + ")";
    }
}
