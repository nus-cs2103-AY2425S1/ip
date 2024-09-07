package socchat.task.event;

import java.time.LocalDateTime;

import socchat.Parser;
import socchat.task.Task;


/**
 * Represents an 'Event' task, which is a type of task that includes a description,
 * start time, and end time.
 * Inherits from the {@link Task} class and provides additional functionality specific
 * to 'Event' tasks.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new 'Event' task with the specified description, start time, and end time.
     * The task is initially set as not done.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
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
    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + Parser.dateToString(from) + ", to: "
                + Parser.dateToString(to) + ")";
    }

    /**
     * Returns a string representation of the 'Event' task for saving to a file.
     * The format of the string is "E | [Done/Not Done] | description | start time to end time".
     *
     * @return a string representation of the 'Event' task suitable for saving
     */
    public String toSave() {
        return "E" + " | " + super.getDoneStatus()
                + " | " + super.getDescription()
                + " | " + Parser.dateToString(from)
                + " to " + Parser.dateToString(to);
    }
}
