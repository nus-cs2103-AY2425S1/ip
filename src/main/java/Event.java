public class Event extends Task {

    protected String fromDuration;
    protected String toDuration;

    public Event(String description, String fromDuration, String toDuration) {
        super(description);
        this.toDuration = toDuration;
        this.fromDuration = fromDuration;
        Task.taskCount++;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDuration + " to: " + this.toDuration + ")";
    }
}
