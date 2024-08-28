package yapbot.tasks;

/**
 * Child class of Task that has a start and end dates/times.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Returns an Event instance.
     *
     * @param description Details of the Task.
     * @param from Date/time when this task should start.
     * @param to Date/time when this task should end.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + this.from + " To: " + this.to + ")";
    }
}
