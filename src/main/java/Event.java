public class Event extends Task {
    private String start;
    private String end;
    public Event(String body, String start, String end) {
        super(body);
        this.start = start;
        this.end = end;
    }

    public Event(String body, boolean isDone, String start, String end) {
        super(body, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), this.start, this.end);
    }

    @Override
    public String saveString() {
        return "event," + super.saveString() + "," + this.start + "," + this.end;
    }
}
