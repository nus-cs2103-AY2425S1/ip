public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "E | 1 | " + this.getDescription() + " | " + this.from + " | " + this.to;
        } else {
            return "E | 0 | " + this.getDescription() + " | " + this.from + " | " + this.to;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
