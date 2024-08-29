package skibidi.task;

public class Event extends AbstractTask {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String marker, String description, String from, String to) {
        super(marker, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ")" + " (to: " + to + ")";
    }

    public String serialize() {
        return String.join("|", new String[]{"E", getStatusIcon(), description, from, to});
    }
}
