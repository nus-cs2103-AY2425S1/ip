public class EventTask extends Task {
    protected String start;
    protected String end;

    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public EventTask(String desc, String start, String end, boolean isDone) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s", super.toString(),
                start, end);
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + start + " | " + end;
    }
}
