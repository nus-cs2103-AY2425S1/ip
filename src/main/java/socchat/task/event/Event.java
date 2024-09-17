package socchat.task.event;

import java.time.LocalDate;

import parser.DateParser;
import socchat.task.Task;


/**
 * Represents an 'Event' task, which is a type of task that includes a description,
 * start time, and end time.
 * Inherits from the {@link Task} class and provides additional functionality specific
 * to 'Event' tasks.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs a new 'Event' task with the specified description, start time, and end time.
     * The task is initially set as not done.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to, String tagName) {
        super(description, tagName);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new 'Event' task with the specified description, start time, end time, and completion status.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @param isDone the completion status of the event
     */
    public Event(String description, LocalDate from, LocalDate to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;

    }
    public Event(String description, LocalDate from, LocalDate to, Boolean isDone, String tagName) {
        super(description, isDone, tagName);
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + DateParser.dateToString(from) + ", to: "
                + DateParser.dateToString(to) + ")"
                + "<tag: " + tagName + ">";
    }

    /**
     * Returns a string representation of the 'Event' task for saving to a file.
     * The format of the string is "E | [Done/Not Done] | description | start time to end time".
     *
     * @return a string representation of the 'Event' task suitable for saving
     */
    public String toSave() {
        return "E" + " | " + super.getDoneStatus()
                + " | " + description
                + " | " + DateParser.dateToString(from)
                + " | " + DateParser.dateToString(to)
                + " | " + tagName;
    }
}
