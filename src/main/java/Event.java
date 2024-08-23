public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String toStr = this.to == null ? "" : " to: " + this.to;
        return String.format("[E]%s (from: %s%s)", super.toString(), this.from, toStr);
    }
}
