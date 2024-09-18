package elsa.task;

/**
 * Represents a task that is an event.
 * @author Aaron
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an Event task with the specified description, completion status, start time, and end time.
     *
     * @param description A description of the event.
     * @param isDone True if the task is completed, false otherwise.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getStart() {
        return start;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getEnd() {
        return end;
    }

    /**
     * Returns a string representation of the event, including the type of task, the completion status, and the
     * event's start and end times.
     *
     * @return A string representing the event task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
