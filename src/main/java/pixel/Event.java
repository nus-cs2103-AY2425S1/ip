package pixel;

/**
 * Represents a Task that has a name, a start datetime and an end datetime
 */
public class Event extends Task {
    protected String from; // start datetime of Event
    protected String to; // end datetime of Event

    /**
     * Constructor method for Event
     *
     * @param description name of Event
     * @param from start dateTime of Event
     * @param to end dateTime of Event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event
     * when user types in the command list
     *
     * @return string representation of Event for printing
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string representation of the Event
     * that will be saved to the file
     *
     * @return string representation of Event for saving in file
     */
    @Override
    public String getFileString() {
        return String.format("E | %s | %s | %s to %s", getStatusNumber(), getDescription(), from, to);
    }
}
