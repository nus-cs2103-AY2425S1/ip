package BrainRot;

public class Event extends Task {
    protected String end;
    protected String start;
    public Event(String command, String start, String end) {
        super(command);  // Only pass the task part to BrainRot.Task
        this.start = start;
        this.end = end;

    }
    @Override
    public String toFileString() {
        return "[E][" + (isDone ? "1" : "0") + "]/" + description + "/" + start + "/" + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.start + " to: " + this.end + ")";
    }
}
