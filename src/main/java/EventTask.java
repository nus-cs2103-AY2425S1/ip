public class EventTask extends Task {
    private final String startTime;
    private final String endTime;

    public EventTask(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String simpleFormat() {
        return "E | " + super.simpleFormat() + " | " + this.startTime + " | " + this.endTime;
    }
}
