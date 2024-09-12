package henry.task;

/**
 * Represents an event to be recorded. An <code>Event</code> object
 * is represented by three Strings.
 * e.g., <code>project meeting, Mon 2pm, to 4pm</code>
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Sets up the instance.
     *
     * @param description Description of the task.
     * @param startTime Start time of the task.
     * @param endTime End time of the task.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Sets up the instance.
     *
     * @param description Description of the task.
     * @param startTime Start time of the task.
     * @param endTime End time of the task.
     * @param isDone Whether the task is completed.
     */
    public Event(String description, String startTime, String endTime,
                 boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns summary of task.
     *
     * @return Task summary.
     */
    @Override
    public String summary() {
        return "E " + super.summary() + " | " + this.startTime
                + "-" + this.endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime
                + " to: " + this.endTime + ")";
    }
}
