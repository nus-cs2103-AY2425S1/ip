public class Event extends Task {

    private final String start;
    private final String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String getFileString() {
        return getTaskType() + " | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + start + " | " + end;
    }
}