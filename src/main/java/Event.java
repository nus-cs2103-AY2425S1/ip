public class Event extends Task {
    private String at;
    private String to;

    public Event(String description, String at, String to) {
        super(description);
        this.at = at;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + " to: " + to + ")";
    }
}
