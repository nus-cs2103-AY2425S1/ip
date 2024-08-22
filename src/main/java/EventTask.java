public class EventTask extends Task{

    private final String start;
    private final String end;

    public EventTask(String taskName, String start, String end) {
        super(taskName);
        this.start = start.trim();
        this.end = end.trim();
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)","E", super.toString(), this.start, this.end);
    }
}
