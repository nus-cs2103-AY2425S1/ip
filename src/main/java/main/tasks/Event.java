package main.tasks;

/**
 * Event represents an event task and is a subclass of the Task class.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * A constructor for Event.
     * @param description Description of the event as inputted by the user.
     * @param from Date and/ or time representing the start of the event.
     * @param to Date and/or time representing the end of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E .. " + super.toFileFormat() + " .. " + this.from + " .. " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(this.from)
                + " to: " + formatDate(this.to) + ")";
    }
}
