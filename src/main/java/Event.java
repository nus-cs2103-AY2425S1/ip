public class Event extends Task {
    private String from;
    private String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String markDone() {
        super.markDone();
        return this.toString();
    }
    @Override
    public String unmarkDone() {
        super.unmarkDone();
        return this.toString();
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.from + " to: " + this.to + " )";
    }
}
