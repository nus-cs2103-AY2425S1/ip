public class Event extends Task {
    private final String from;
    private final String to;
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }
    @Override
    public String exportString() {
        String completedString;
        if (this.isCompleted()) {
            completedString = "1";
        } else {
            completedString = "0";
        }
        return String.format("E|%s|%s|%s|%s", completedString, this.getTaskName(), this.from, this.to);
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                                super.toString(),
                                this.from,
                                this.to);
    }
}
