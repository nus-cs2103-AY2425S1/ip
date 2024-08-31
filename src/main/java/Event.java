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
        return String.format("%s (Event: from %s to %s) %s", printStatus(), from, to, getDescription());
    }

    public String toSaveFile() {
        return String.format("E:%s:%s:%s:%s", isDone() ? "x" : "", getDescription(), from, to);
    }
}
