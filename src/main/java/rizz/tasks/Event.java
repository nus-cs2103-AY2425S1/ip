package rizz.tasks;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String text, String from, String to, boolean isDone) {
        super(text,isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String export() {
        return "E | " + (isDone ? "1" : "0") + " | " + text + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}