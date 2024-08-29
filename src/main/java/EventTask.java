public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String taskItem, String from, String to, boolean isCompleted) {
        super(taskItem, isCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toDatabaseFormat() {
        return "E | " + (this.isCompleted() ? "1" : "0") + " | " + this.getTaskItem() + " | " + this.from + " | " + this.to;
    }
}
