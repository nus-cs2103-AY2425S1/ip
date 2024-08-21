/**
 * Represents an event task with a description, start time, and end time.
 * This class extends the Task class and provides specific functionality
 * for event tasks.
 */
public class EventsTask extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructs an EventsTask with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime The starting time of the event.
     * @param endTime The ending time of the event.
     */
    public EventsTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the event task, including its
     * status (done or not), description, start time, and end time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.startTime,
                this.endTime);
    }
}
