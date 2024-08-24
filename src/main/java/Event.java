public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an Event task with the specified description, start time, and end time
     *
     * @param description The description of the task provided by the user
     * @param start The start time of the task provided by the user
     * @param end The end time of the task provided by the user
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event object.
     * The string includes a "[E]" prefix to indicate that this is an Event task,
     * followed by the string representation provided by the Task superclass.
     *
     * @return A string in the format: "[E] <super.toString()>"
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
