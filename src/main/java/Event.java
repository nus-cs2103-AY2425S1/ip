public class Event extends Task {
    String start;
    String end;
    public Event(String s, String start, String end) {
        super(s);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)" , super.toString(), this.start, this.end);
    }
}
