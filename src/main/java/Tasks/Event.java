package Tasks;

import enums.TaskType;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description,  String start, String end) {
        super(description, TaskType.DEADLINE);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
