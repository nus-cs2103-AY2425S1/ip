public class Event extends Task {

    private String from;
    private String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String getState() {
        return String.format("E | %s | %s | %s", super.getState(), this.from, this.to);
    }
}
