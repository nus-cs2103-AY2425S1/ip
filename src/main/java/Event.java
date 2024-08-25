public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to, TaskType type) {
        super(description, type);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to, TaskType type) {
        super(description, isDone, type);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String formatToSave() {
        return super.formatToSave() + " | " + from + " | " + to;
    }
}