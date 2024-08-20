public class EventTask extends Task {
    protected String start;
    protected String end;

    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s", super.toString(),
                start, end);
    }
}
