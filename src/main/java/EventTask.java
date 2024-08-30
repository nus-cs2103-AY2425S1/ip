/**
 * EventTask class that inherit from Task.
 * A kind of task that has a start and end time
 */
public class EventTask extends Task {
    private String start;  // starting time of task
    private String end;    // ending time of task

    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }
    @Override
    public String toFileString() {
        return "E | " + super.getStatusIcon() + " | " + getDescription() + " | " + start + " | " + end;
    }
}
