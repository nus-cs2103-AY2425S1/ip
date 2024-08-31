public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        type = TaskType.EVENT;
    }

    protected String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (from: %s to: %s)",
                getTaskType(), super.toString(), from, to);
    }
}
