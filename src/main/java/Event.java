public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s (Event: from %s to %s) %s", getStatus(), from, to, getDescription());
    }
}
