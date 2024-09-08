package Tasks;

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
        return String.format("[Event] " + super.toString() + " From: [%s] To: [%s]", this.from, this.to);
    }
}
