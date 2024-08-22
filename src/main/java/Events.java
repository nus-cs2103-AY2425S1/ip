public class Events extends Task {
    String start;
    String end;
    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
