public class Event extends Task {
    public Event(String description) {
        super(description.replace("/from", "(from:").replace("/to", "to:") + ")");
        if (description.isEmpty() || description.equals("event")) {
            throw new DukeException("event", "OOPS!!! The description of a event shouldn't be empty!\n");
        }
    }
    public String getString() {
        return "[E]" + super.getString();
    }
}
