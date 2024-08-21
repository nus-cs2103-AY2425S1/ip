public class Event extends Task {
    public Event(String description) {
        super(description.replace("/from", "(from:").replace("/to", "to:") + ")");
    }
    public String getString() {
        return "[E]" + super.getString();
    }
}
