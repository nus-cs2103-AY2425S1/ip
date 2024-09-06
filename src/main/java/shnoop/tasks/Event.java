package shnoop.tasks;

import shnoop.exceptions.*;

/**
 * Represents a Task that has a start and end timing / date.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates an Event instance based on the given information and input.
     *
     * @param description Description of the Task.
     * @param from Time that the Event begins.
     * @param to Time that the Event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event instance based on the given information and input, including if it should be marked.
     *
     * @param description Description of the Task.
     * @param from Time that the Event begins.
     * @param to Time that the Event ends.
     * @param done True if Event should be marked when created, false if otherwise.
     */
    public Event(String description, String from, String to, boolean done) {
        super(description);
        this.from = from;
        this.to = to;
        if (done) {
            this.markTask();
        }
    }

    /**
     * Creates an Event instance based on the given input. Reads String input to determine if it is a valid Event.
     *
     * @param str Input to be read.
     * @throws IncompleteEventOrDeadlineException If input does not indicate a valid Event.
     */
    public Event(String str) throws IncompleteEventOrDeadlineException {
        super(str);
        if (!str.contains("/from ") || !str.contains("/to ")) {
            throw new IncompleteEventOrDeadlineException();
        } else {
            try {
                String desc = str.substring(6, str.toLowerCase().indexOf("/from "));
                description = desc;
                from = parseDate(str.substring(str.toLowerCase().indexOf("/from ") + 6,
                        str.toLowerCase().indexOf("/to ") - 1)) + " ";
                to = parseDate(str.substring(str.toLowerCase().indexOf("/to ") + 4, str.length()));
                if (from.isEmpty() || to.isEmpty() || desc.isEmpty()) {
                    throw new IncompleteEventOrDeadlineException();
                }
            } catch (IncompleteEventOrDeadlineException e) {
                System.out.println("Stop slacking off"); //TODO PUT PROPER WORD HERE
            }
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + "to: " + to + ")";
    }

    @Override
    public String toUniqueFileString() {
        String s = super.toUniqueFileString();
        s += "002"; // Unique identifier for Event Tasktype
        s += super.description;
        s += "/from/";
        s += from;
        s += "/to/";
        s += to;
        return s;
    }
}
