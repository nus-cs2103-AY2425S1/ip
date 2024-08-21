public class Event extends Task {
    private String start;
    private String end;
    public Event(String body, String start, String end) {
        super(body);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String str = String.format("[E]%s (from: %s, to: %s)", super.toString(), this.start, this.end);
        return str;
    }
}
