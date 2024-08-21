public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
