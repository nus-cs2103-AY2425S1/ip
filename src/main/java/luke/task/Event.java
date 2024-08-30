package luke.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskName, String from, String to, boolean isDone) {
        super(taskName, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String taskDescription() {
        return "[E]" + showMark() + " " + this.name
                + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String taskInSaveData() {
        return (isDone ? "X" : "-")
                + " | event | "
                + name + " from " + from + " to " + to + "\n";
    }
}
