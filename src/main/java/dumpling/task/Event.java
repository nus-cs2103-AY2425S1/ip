package dumpling.task;

/**
 * Event class, a special type of task
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Event constructor.
     * @param description Description of event
     * @param from Starting date / date time of event
     * @param to Ending date / date time of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskForSaving() {
        return String.format("E | %d | %s | %s-%s\n", (
                this.isDone ? 1 : 0),
                this.description,
                this.from,
                this.to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.from, this.to);
    }
}
