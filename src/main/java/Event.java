public class Event extends Task {
    String from;
    String to;
    public Event(String description, String from, String to) {
        super(description);
        if (description.isEmpty() || description.equals("event")) {
            throw new DukeException("event", "OOPS!!! The description of a event shouldn't be empty!\n");
        }
        this.from = from;
        this.to = to;
    }
    public String getString() {
        return "[E]" + super.getString() + " (from: " + from + " to: " + to + ")";
    }
}
