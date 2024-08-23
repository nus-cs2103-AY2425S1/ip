public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String taskInfo, String start, String end) {
        super(taskInfo);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String s = "[E] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo() + " (from: " + this.start + " to: " + this.end + ")";
        return s;
    }
}
