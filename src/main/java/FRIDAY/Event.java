package FRIDAY;

public class Event extends Task {
    private String startTime, endTime;
    public Event(String description, String start, String end, int type) {
        super(description, type);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String storageDisplay() {
        return "E" + super.storageDisplay() + " | from " + startTime + " to " + endTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() +  "(" + startTime + " to " + endTime + ")";
    }
}
