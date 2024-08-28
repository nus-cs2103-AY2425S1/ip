public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("EVENT | %s | %s | %s | %s\n", status, this.description, this.from, this.to);
    }
}
