public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String taskItem, String from, String to) {
        super(taskItem);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
