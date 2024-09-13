package secondmind;

/**
 * Represents an event task with a start and end time.
 * This class extends the {@link Task} class to include details
 * about the event's start and end times.
 */
public class EventTask extends Task {
    private String eventStart;
    private String eventEnd;

    /**
     * Constructs a new {@code EventTask} with the specified task description,
     * start time, and end time.
     *
     * @param task       The description of the event task.
     * @param eventStart The start time of the event.
     * @param eventEnd   The end time of the event.
     */
    public EventTask(String task, String eventStart, String eventEnd) {
        super(task);
        assert eventStart != null: "eventStart must not be null";
        assert eventEnd != null: "eventEnd must not be null";
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Returns a string representation of the event task formatted for storage.
     * The format includes the task type, completion status, task description,
     * event start time, and event end time.
     *
     * @return The string representation of the event task for storage.
     */
    @Override
    public String getStorageRepresentation() {
        if (!this.isdone) {
            return "E|0|" + this.description + "|" + this.eventStart + "|" + this.eventEnd;
        } else {
            return "E|1|" + this.description + "|" + this.eventStart + "|" + this.eventEnd;
        }
    }

    /**
     * Returns a string representation of the event task.
     * The format includes the task type, completion status, task description,
     * event start time, and event end time.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[E]");
        sb.append(super.toString());
        sb.append(" (from: " + this.eventStart + " to: " + this.eventEnd + ")");
        return sb.toString();
    }
}