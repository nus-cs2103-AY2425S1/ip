public class Event extends Task {
    protected String fromTime;
    protected String toTime;

    public Event (String TaskName, String fromTime, String toTime) {
        super(TaskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.fromTime, this.toTime);
    }
}