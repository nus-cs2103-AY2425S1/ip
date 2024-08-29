package sage.Task;

public class EventTask extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an EventTask with a description, start date, and end date.
     *
     * @param description The description of the task.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSave() {
        return String.format("E | %s | %s | %s", super.toSave(), from, to);
    }
}
