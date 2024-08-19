public class EventTask extends Task {
    private String start;
    private String end;

    /**
     * Constructor to create a EventTask object
     *
     * @param description that describes what the task is
     * @param start of the event
     * @param end of the event
     */
    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * String representation of the task
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
