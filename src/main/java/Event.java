public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String taskDescription() {
        return "[E]" + showMark() + " " + this.name
                + " (from: " + from + " to: " + to + ")";
    }
}
