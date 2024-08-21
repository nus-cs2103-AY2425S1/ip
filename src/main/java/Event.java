public class Event extends Task {
    public String startTime;
    public String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return taskIsDone ? "[E][X] " + this.description + " (from: " + startTime + " to: " + endTime + ")" : "[E][ ] " + this.description + " (from: " + startTime + " to: " + endTime + ")";
    }
}
