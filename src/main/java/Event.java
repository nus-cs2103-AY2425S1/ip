public class Event extends Task {
    protected String fromTime;
    protected String toTime;

    public Event (String taskName, String fromTime, String toTime) {
        super(taskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Event (String taskName, String fromTime, String toTime, boolean isDone) {
        super(taskName, isDone);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.fromTime, this.toTime);
    }

    @Override
    public String commaString() {
        return String.format("E,%s,%s,%s",
                super.commaString(), this.fromTime, this.toTime);
    }
}