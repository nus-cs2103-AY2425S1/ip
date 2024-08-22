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
        String timeframe = String.format("(from: %s to: %s)", this.from, this.to);
        return String.format("[E]%s %s", super.toString(), timeframe);
    }
}
