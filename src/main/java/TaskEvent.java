public class TaskEvent extends Task {
    private String from;
    private String to;

    public TaskEvent(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    @Override
    public String toSaveString() {
        return String.format("E|%d|%s|%s|%s", isDone() ? 1 : 0, getTask(), from, to);
    }
}
