public class Event extends Task {
    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toSave() {
        return "E" + " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.start + " | " + this.end;
    }
}
