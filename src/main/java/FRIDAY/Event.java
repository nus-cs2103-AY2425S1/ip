package FRIDAY;

public class Event extends Task {
    private String start, end;
    public Event(String description, String start, String end, int type) {
        super(description, type);
        this.start = start;
        this.end = end;
    }

    @Override
    public String storageDisplay() {
        return "E" + super.storageDisplay() + " | from " + start + " to " + end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() +  "(" + start + " to " + end + ")";
    }
}
