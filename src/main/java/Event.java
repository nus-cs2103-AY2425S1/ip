public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.startTime = start;
        this.endTime = end;
    }

    public String toString() { //prototype in case of future modification
        return "[E]" + super.toString() + " from: " + this.startTime + " |to: " + this.endTime;
    }
}
