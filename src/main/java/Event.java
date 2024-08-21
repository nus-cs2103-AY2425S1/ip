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
        return "[E]" + (isDone ? "[X]" : "[ ]") + desc +" (from: " + this.from + " to: " + this.to + ")";
    }
}
