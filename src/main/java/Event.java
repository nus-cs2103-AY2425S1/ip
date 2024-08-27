public class Event extends Task{
    protected String to;
    protected String from;
    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " +  to + ")";
    }

    @Override
    public String save() {
        return "E | " + super.save() + " | " + from + " | " + to;
    }
}
