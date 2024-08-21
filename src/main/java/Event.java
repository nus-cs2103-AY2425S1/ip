public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        // Constructor for Event class
        super(description);
        this.to = to;
        this.from = from;
    }
    @Override
    public String toString() {
        // Add a [D] at the front of task description (parent class)
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
