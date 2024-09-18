package Alex.Task;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String starttime;
    protected String endtime;

    /**
     * Constructs an Event with the given description, start time, and end time.
     * @param description The description of the event.
     * @param starttime The start time of the event.
     * @param endtime The end time of the event.
     */
    public Event(String description, String starttime, String endtime) {
        super(description);
        this.starttime = starttime;
        this.endtime = endtime;
    }

    /**
     * Gets the start time of the event.
     * @return The start time of the event.
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * Gets the end time of the event.
     * @return The end time of the event.
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * Gets the string representation of the Event task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + starttime + ", to: " + endtime + ")";
    }

    /**
     * Gets the type of the task.
     * @return The type of the task (EVENT).
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }
}

