public class Event extends Task{
    protected String from, to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, "event", isDone);

        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E|" + isDone + "|" + description + "|" + from + "/to " + to + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
