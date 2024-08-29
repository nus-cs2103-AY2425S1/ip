public class Event extends Task {
    private String from;
    private String to;

    public Event(String description) throws UnknownTimeException {
        super(description.substring(0, description.indexOf("/from") - 1));
        this.from = description.substring(description.indexOf("/from") + 6,
                description.indexOf("/to") - 1);
        this.to = description.substring(description.indexOf("/to") + 4);
    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    public String fileString() {
        return super.fileString() + " | " + this.from + " | " + this.to;
    }
}
