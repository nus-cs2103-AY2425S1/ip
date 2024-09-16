package com.appleaster.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Appleaster application.
 * This class extends the Task class and adds start and end time attributes.
 */
public class Event extends Task {
    /** The start time of the event */
    protected LocalDateTime from;

    /** The end time of the event */
    protected LocalDateTime to;

    /** The formatter for parsing input date strings */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** The formatter for formatting output date strings */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event in the format "yyyy-MM-dd HHmm"
     * @param to the end time of the event in the format "yyyy-MM-dd HHmm"
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    /**
     * Gets the start time of the event.
     *
     * @return the start time as a LocalDateTime object
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return the end time as a LocalDateTime object
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return a string representation of the event, including its type, status, description, start time, and end time
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)", type.getSymbol(), status.getSymbol(), description, 
                             from.format(OUTPUT_FORMAT), to.format(OUTPUT_FORMAT));
    }
}