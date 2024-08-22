public class EventTask extends Task {
    private String start;
    private String end;

    public EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s",
                super.toSaveString(), start, end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), start, end);
    }
}
