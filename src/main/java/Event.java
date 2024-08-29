public class Event extends Task {

    // start date/time
    String from;
    // end date/time
    String to;

    /**
     * Constructor for Event
     *
     * @param description description of task
     * @param from starting date/time
     * @param to ending date/time
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event with status
     *
     * @param description description of task
     * @param from starting date/time
     * @param to ending date/time
     * @param isComplete status of task
     */
    public Event(String description, String from, String to, boolean isComplete) {
        super(description, TaskType.EVENT, isComplete);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string representation of Event for file writing
     *
     * @return String formatted by Task including start and end date/time
     */
    @Override
    public String getSaveFormat() {
        return super.getSaveFormat() + " | " + from + " | " + to;
    }

    /**
     * Returns String representation of Event
     *
     * @return "[E]" with String representation of task including start and end date/time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: "+ to + ")";
    }

}
