public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String description, String start, String end) {
        super(TaskType.EVENT, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "%s (from: %s to: %s)".formatted(super.toString(), start, end);
    }
}
