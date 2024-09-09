package twilight;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(boolean isDone, String description, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + this.startTime + " to: " + this.endTime;
    }

    @Override
    public String toStorageString() {
        return "E," + super.toStorageString() + "," + startTime + "," + endTime;
    }
}
