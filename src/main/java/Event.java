public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X]" : "[ ]") + " " + description + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
