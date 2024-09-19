package bob.tasks;

/**
 * Represents an Event Task with a start and end time.
 * Inherits from the Task class, and includes additional details specific
 * to Event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description the description of the event task.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string representation of the Event task for saving to a file.
     * The format includes the task type, completion status, description, start time, and end time.
     */
    @Override
    public String fileFormat() {
        return "E | " + super.fileFormat() + " | " + this.from + " | " + this.to;
    }

    /**
     * Updates the end time of the event.
     *
     * @param newEndTime the new end time to update to.
     */
    public void updateEndTime(String newEndTime) {
        this.to = newEndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
