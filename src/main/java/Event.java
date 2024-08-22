public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = from; // store the start time
        this.to = to; // store the end time
    }

    @Override
    public String toFileFormat() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}