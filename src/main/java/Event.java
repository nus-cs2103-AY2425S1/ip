public class Event extends Task {

    // start date/time
    String from;
    // end date/time
    String to;

    /**
     * Constructor for Event
     * @param description description of task
     * @param from starting date/time
     * @param to ending date/time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns String representation of Event
     * @return "[E]" with String representation of task including start and end date/time
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: "+ to + ")";
    }

}
