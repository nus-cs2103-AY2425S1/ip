package demurebot.task;

/**
 * Represents an Event task in the DemureBot application.
 * An Event task has a description, a start time, an end time, and a status indicating whether it is done.
 */
public class Event extends Task {

    protected String start;
    protected String end;

    /**
     * Constructs a new Event task with the specified description, start time, end time, and status.
     *
     * @param description The description of the Event task.
     * @param start The start time of the Event task.
     * @param end The end time of the Event task.
     * @param isDone The status of the Event task, true if the task is done, false otherwise.
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}

