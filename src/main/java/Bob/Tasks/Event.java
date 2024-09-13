package bob.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String fileFormat() {
        return "E | " + super.fileFormat() + " | " + this.from + " | " + this.to;
    }

    public void updateEndTime(String newEndTime) {
        this.to = newEndTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
