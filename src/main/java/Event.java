public class Event extends Task {

    private String to;
    private String from;
    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

}
