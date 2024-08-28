public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return this.from;
    }
    public String getTo() {
        return this.to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + to + ")";
    }
}
