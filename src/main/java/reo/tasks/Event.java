package reo.tasks;

/** Represents the Task type Event */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for the Event class.
     *
     * @param name The name of the event.
     * @param isDone The completion status of the event.
     * @param to The ending time of the event.
     * @param from The starting time of the event.
     */
    public Event(String name, boolean isDone, String to, String from) {
        super(name, isDone);
        this.to = to;
        this.from = from;
    }

    /**
     * toString method for the Event class.
     *
     * @return The string representation of the event to be displayed to user.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the line to be written to the file to represent the event object.
     *
     * @return The string representation of the event to be written to memory.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from + " | " + to;
    }
}
